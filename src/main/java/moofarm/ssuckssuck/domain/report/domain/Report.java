package moofarm.ssuckssuck.domain.report.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moofarm.ssuckssuck.domain.certification.domain.Certification;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static moofarm.ssuckssuck.domain.report.domain.ProcessingStatus.RECEIPT;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Report {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Enumerated(EnumType.STRING)
    private ProcessingStatus ProcessingStatus;

    private String reportReason;

    @Builder
    public Report(Certification certification, String reportReason, ReportType reportType, ProcessingStatus ProcessingStatus) {
        this.certification = certification;
        this.reportReason = reportReason;
        this.reportType = reportType;
        this.ProcessingStatus = ProcessingStatus;
    }

    public static Report createReport(Certification certification, String reportReason, ReportType reportType) {
        return builder()
                .certification(certification)
                .reportReason(reportReason)
                .reportType(reportType)
                .ProcessingStatus(RECEIPT)
                .build();
    }

    public void updateProcessingStatus(ProcessingStatus ProcessingStatus) {
        this.ProcessingStatus = ProcessingStatus;
    }
}
