package com.elias.site_generation.adapter.theme.out.generation.component.template;

import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.shared.props.TemplateProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
final class TemplateComponentsApplier {

    private final TemplateProps props;
    private static final String STYLE_ELEMENT = "style", SOURCE_ELEMENT = "src";

    public byte[] applyIndex(TemplateType type, IndexComponent component, Set<String> images) {
        Document html = Jsoup.parse(new String(component.index));

        applyGeneratedStyles(html, new String(component.css));
        applyImagePaths(type, html, images);

        return html.outerHtml().getBytes();
    }

    private void applyGeneratedStyles(Document html, String generatedCss) {
        Element style = html.head().selectFirst(STYLE_ELEMENT);

        if (style == null) {
            style = html.createElement(STYLE_ELEMENT);
            html.head().appendChild(style);
        }

        style.text(generatedCss);
    }

    private void applyImagePaths(TemplateType type, Document html, Set<String> images) {
        Elements imageEls = html.select(props.getImagesClass());
        if (imageEls.size() < images.size()) throw new IllegalStateException("Not enough images for the page.");

        Iterator<String> iterator = images.iterator();
        for (Element imageEl : imageEls) {
            imageEl.attr(SOURCE_ELEMENT, props.getThemesPathTemplate() + type.getName() + iterator.next());
        }
    }

    public record IndexComponent(byte[] index, byte[] css) {
        public static IndexComponent from(byte[] index, byte[] css) {
            return new IndexComponent(index, css);
        }
    }

}
