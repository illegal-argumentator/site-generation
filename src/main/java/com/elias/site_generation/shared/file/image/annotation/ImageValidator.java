package com.elias.site_generation.shared.file.image.annotation;

import com.elias.site_generation.shared.file.image.utils.ImageUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ImageValidator implements ConstraintValidator<ImageFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return ImageUtils.isValid(file);
    }

}
