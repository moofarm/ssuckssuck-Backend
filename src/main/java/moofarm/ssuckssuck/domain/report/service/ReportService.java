package moofarm.ssuckssuck.domain.report.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moofarm.ssuckssuck.domain.avatar.domain.Avatar;
import moofarm.ssuckssuck.domain.avatar.service.AvatarServiceUtils;
import moofarm.ssuckssuck.domain.certification.domain.Certification;
import moofarm.ssuckssuck.domain.certification.service.CertificationServiceUtils;
import moofarm.ssuckssuck.domain.report.domain.Report;
import moofarm.ssuckssuck.domain.report.domain.repository.ReportRepository;
import moofarm.ssuckssuck.domain.report.exception.ReportNotFoundException;
import moofarm.ssuckssuck.domain.report.presentation.dto.request.CreateReportRequest;
import moofarm.ssuckssuck.domain.report.presentation.dto.request.ProcessReportRequest;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.global.utils.user.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {

    @Value("${spring.mail.username}")
    private String username;

    private final ReportRepository reportRepository;
    private final UserUtils userUtils;
    private final AvatarServiceUtils avatarServiceUtils;
    private final CertificationServiceUtils certificationServiceUtils;
    private final JavaMailSender javaMailSender;
    private static final String subject = "[쑥쑥쑥] 신고가 접수되었습니다.";

    // 신고 생성
    @Transactional
    public void createReport(CreateReportRequest createReportRequest) {
        Certification certification = certificationServiceUtils.queryCertification(createReportRequest.certificationId());

        Report report = Report.createReport(
                certification,
                createReportRequest.reportReason(),
                createReportRequest.reportType()
        );

        reportRepository.save(report);

        sendMail(certification, createReportRequest);
    }

    // 신고 처리
    @Transactional
    public void processUser(ProcessReportRequest processReportRequest) {
        User user = userUtils.getUserById(processReportRequest.userId());
        Avatar avatar = user.getAvatar();

        avatar.subExperience(Math.max(avatar.getExperience() - 10, 0));
        avatarServiceUtils.updateGrade(avatar);
        avatarServiceUtils.calculateExperienceNeededForNextGrade(avatar);

        Report report = reportRepository.findById(processReportRequest.reportId()).orElseThrow(() -> ReportNotFoundException.EXCEPTION);

        report.updateProcessingStatus(processReportRequest.processingStatus());
    }

    @Async(value = "mailExecutor")
    public void sendMail(Certification certification, CreateReportRequest createReportRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(username);
            mimeMessageHelper.setSubject(subject + " (" + createReportRequest.reportType().getText() + ")");
            String body = "";
            body += "<h1>" + "인증 Id : " + certification.getId() + "<h1>";
            body += "<h1>" + "인증 이미지 url : " + certification.getCertificationImage() + "<h1>";
            body += "<h1>" + "신고 타입 : " + createReportRequest.reportType().getText() + "<h1>";
            body += "<h1>" + "신고 사유 : " + createReportRequest.reportReason() + "<h1>";
            mimeMessageHelper.setText(body, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
