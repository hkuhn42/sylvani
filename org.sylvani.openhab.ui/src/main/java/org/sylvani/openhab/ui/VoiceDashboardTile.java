package org.sylvani.openhab.ui;

import org.openhab.ui.dashboard.DashboardTile;

/**
 * @author hkuhn
 *
 */
public class VoiceDashboardTile implements DashboardTile {

    /*
     * (non-Javadoc)
     *
     * @see org.openhab.ui.dashboard.DashboardTile#getName()
     */
    @Override
    public String getName() {
        return "Voice";
    }

    @Override
    public String getUrl() {
        return "../voice/index.html";
    }

    @Override
    public String getOverlay() {
        return "html5";
    }

    @Override
    public String getImageUrl() {
        return "../ui/img/dashboardtile.png";
    }

}
