package com.elias.site_generation.application.template;

import com.elias.site_generation.domain.image.Image;
import com.elias.site_generation.domain.template.Template;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.exception.TemplateNotFoundException;
import com.elias.site_generation.port.image.ImageQueryPort;
import com.elias.site_generation.port.template.TemplateCommandPort;
import com.elias.site_generation.port.template.TemplateFileQueryPort;
import com.elias.site_generation.port.template.TemplateQueryPort;
import com.elias.site_generation.port.template.TemplateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService implements TemplateUseCase {

    private final ImageQueryPort imageQueryPort;

    private final TemplateFileQueryPort templateFileQueryPort;
    private final TemplateQueryPort templateQueryPort;
    private final TemplateCommandPort templateCommandPort;

    @Override
    public void save(Long imageId, Template template) {
        throwIfTemplateNotExists(template.type());
        templateCommandPort.save(tryWithImage(imageId, template));
    }

    @Override
    public List<Template> findAll() {
        return templateQueryPort.findAll();
    }

    private Template tryWithImage(Long imageId, Template template) {
        if (imageId != null) {
            Image image = imageQueryPort.findById(imageId);
            return template.withImage(image);
        }

        return template;
    }

    private void throwIfTemplateNotExists(TemplateType type) {
        if (!templateFileQueryPort.exists(type)) {
            throw new TemplateNotFoundException("Template not found.");
        }
    }

}
