package com.richfinancial.qiyifinance.utils;

import android.content.Context;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

//为了解决内存溢出问题，使用imageloader加载https路径图片
public class HttpsImageDownloader extends BaseImageDownloader {

    /** Https 证书验证对象 */
    private static SSLContext s_sSLContext = null;

        private SSLSocketFactory mSSLSocketFactory;
        public HttpsImageDownloader(Context context) {
            super(context);
            SSLContext sslContext = getSSLContext(context);
            mSSLSocketFactory = sslContext.getSocketFactory();
        }
        public HttpsImageDownloader(Context context, int connectTimeout, int readTimeout) {
            super(context, connectTimeout, readTimeout);  
            SSLContext sslContext = getSSLContext(context);
            mSSLSocketFactory = sslContext.getSocketFactory();  
        }  
        @Override  
        protected InputStream getStreamFromNetwork(String imageUri, Object extra) throws IOException {
            URL url = null;
            try {  
                url = new URL(imageUri);  
            } catch (MalformedURLException e) {
            }  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(connectTimeout);  
            conn.setReadTimeout(readTimeout);  
  
            if (conn instanceof HttpsURLConnection) {
                ((HttpsURLConnection)conn).setSSLSocketFactory(mSSLSocketFactory);  
                ((HttpsURLConnection)conn).setHostnameVerifier((DO_NOT_VERIFY));  
            }  
            return new BufferedInputStream(conn.getInputStream(), BUFFER_SIZE);
        }  
        // always verify the host - dont check for certificate  
        final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            @Override  
            public boolean verify(String hostname, SSLSession session) {
                return true;  
            }  
        };


    /**
     * 获取Https的证书
     * @param context Activity（fragment）的上下文
     * @return SSL的上下文对象
     */
    public static SSLContext getSSLContext(Context context) {
        if (null != s_sSLContext) {
            return s_sSLContext;
        }

        //以下代码来自百度 参见http://www.tuicool.com/articles/vmUZf2
        CertificateFactory certificateFactory = null;

        InputStream inputStream = null;
        KeyStore keystore = null;
        String tmfAlgorithm = null;
        TrustManagerFactory trustManagerFactory = null;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            inputStream = context.getAssets().open("server_211.crt");//这里导入SSL证书文件
            //inputStream = context.getAssets().open("51p2b_server_bs.pem");//这里导入SSL证书文件

            Certificate ca = certificateFactory.generateCertificate(inputStream);

            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(null, null);
            keystore.setCertificateEntry("ca", ca);

            tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            trustManagerFactory = TrustManagerFactory.getInstance(tmfAlgorithm);
            trustManagerFactory.init(keystore);

            // Create an SSLContext that uses our TrustManager
            s_sSLContext = SSLContext.getInstance("TLS");
            s_sSLContext.init(null, trustManagerFactory.getTrustManagers(), null);
            return s_sSLContext;
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    }