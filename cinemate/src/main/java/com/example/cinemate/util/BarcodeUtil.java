package com.example.cinemate.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.ByteArrayOutputStream;

public class BarcodeUtil {

    private static final String STATIC_URL =
            "https://yt3.googleusercontent.com/0Wk1sRZ-V17WPh-QcpNo1GvZj8cqfgTy49JexcDODbN2q5Pgw4C-a9tiYUcOflsmO2ixdz2oGw=s160-c-k-c0x00ffffff-no-rj";

    private BarcodeUtil() {}

    public static byte[] generateStaticQRCode() {
        try {
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(STATIC_URL, BarcodeFormat.QR_CODE, 300, 300);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "PNG", out);

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate static QR code", e);
        }
    }
}