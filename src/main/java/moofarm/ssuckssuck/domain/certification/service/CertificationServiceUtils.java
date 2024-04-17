package moofarm.ssuckssuck.domain.certification.service;

import moofarm.ssuckssuck.domain.certification.domain.Certification;

public interface CertificationServiceUtils {
    void deleteCertification(Long id);
    Certification queryCertification(Long id);
}
