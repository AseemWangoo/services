package com.imagefetcher.background_image_fetcher_example.http;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadData {

    private static final int SUCCESS = 200;

    public byte[] downloadData(String requestUrl) {
        byte[] response = new byte[1024];

        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        URL url = null;

        try {
            url = new URL(requestUrl);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");

            int statusCode = urlConnection.getResponseCode();

            if (statusCode == SUCCESS) {

                inputStream = new BufferedInputStream(urlConnection.getInputStream());

                response = convertInputStreamToString(inputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private byte[] convertInputStreamToString(InputStream inputStream) {

        String line = "";
        String result = "";

        ByteArrayOutputStream _baos = new ByteArrayOutputStream();

        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            byte[] buffer = new byte[1024];
            int read = 0;

            while ((read = bufferedInputStream.read(buffer)) != -1) {
                _baos.write(buffer, 0, read);
            }
            _baos.flush();

            if (null != inputStream) {
                inputStream.close();
                bufferedInputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return _baos.toByteArray();
    }
}
