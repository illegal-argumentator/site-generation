package com.elias.site_generation.application.template;

import com.elias.site_generation.domain.image.Image;
import com.elias.site_generation.domain.template.Template;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.exception.TemplateNotFoundException;
import com.elias.site_generation.port.image.ImageQueryPort;
import com.elias.site_generation.port.template.TemplateCommandPort;
import com.elias.site_generation.port.template.TemplateQueryPort;
import com.elias.site_generation.port.template.TemplateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService implements TemplateUseCase {

    private final ImageQueryPort imageQueryPort;

    private final TemplateQueryPort templateQueryPort;
    private final TemplateCommandPort templateCommandPort;

    @Override
    public void save(Long imageId, Template template) {
        throwIfTemplateNotExists(template.type());
        templateCommandPort.save(tryWithImage(imageId, template));
    }

    private Template tryWithImage(Long imageId, Template template) {
        if (imageId != null) {
            Image image = imageQueryPort.findById(imageId);
            return template.withImage(image);
        }

        return template;
    }

    private void throwIfTemplateNotExists(TemplateType type) {
        if (!templateQueryPort.exists(type)) {
            throw new TemplateNotFoundException("Template not found.");
        }
    }

}
