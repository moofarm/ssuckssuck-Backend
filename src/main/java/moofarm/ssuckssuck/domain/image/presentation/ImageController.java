package moofarm.ssuckssuck.domain.image.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.image.presentation.dto.response.UploadImageResponse;
import moofarm.ssuckssuck.domain.image.service.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "이미지", description = "이미지 업로드 관련 API")
@RequiredArgsConstructor
@RestController
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "이미지 업로드")
    @PostMapping("/image")
    public UploadImageResponse upload(@RequestParam("file") MultipartFile file) {
        return imageService.uploadImage(file);
    }
}
