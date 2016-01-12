/**
 *
 */
package org.sylvani.io.voice.http;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.common.util.EList;
import org.eclipse.smarthome.model.sitemap.Sitemap;
import org.eclipse.smarthome.model.sitemap.SitemapProvider;
import org.eclipse.smarthome.model.sitemap.Widget;
import org.eclipse.smarthome.ui.items.ItemUIRegistry;

/**
 * Varianten
 * - Simple and Stupid HAL9000
 * - Volltext
 * - Tagged Analyse
 *
 * @author hkuhn
 *
 */
public class RecognitionRelayServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ItemUIRegistry itemUIRegistry;
    private List<SitemapProvider> sitemaps;

    public RecognitionRelayServlet(ItemUIRegistry itemUIRegistry, List<SitemapProvider> sitemaps) {
        this.itemUIRegistry = itemUIRegistry;
        this.sitemaps = sitemaps;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (SitemapProvider sitemapProvider : sitemaps) {
            for (String sitemapName : sitemapProvider.getSitemapNames()) {
                System.out.println("sitemap " + sitemapName);

                Sitemap sitemap = sitemapProvider.getSitemap(sitemapName);
                EList<Widget> list = sitemap.getChildren();

                for (Widget widget : list) {
                    System.out.println("#Widget " + widget.getLabel() + " " + widget.getItem());
                }

            }
        }
    }

}
