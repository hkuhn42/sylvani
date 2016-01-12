package org.sylvani.client.jmf;

import java.util.Vector;

import javax.media.CaptureDeviceManager;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.FileTypeDescriptor;

public class ListCaptureDevices {
    static ContentDescriptor audioContainer = new ContentDescriptor(FileTypeDescriptor.WAVE);

    public static void main(String[] args) {

        Vector info = CaptureDeviceManager.getDeviceList(audioContainer);
        if (info == null) {
            System.out.println("No Capture devices known to JMF");
        } else {
            System.out.println("The following " + info.size() + " capture devices are known to the JMF");
            for (int i = 0; i < info.size(); i++) {
                System.out.println("\t" + info.elementAt(i));
            }
        }
    }
}