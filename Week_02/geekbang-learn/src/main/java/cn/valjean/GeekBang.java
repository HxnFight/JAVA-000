package cn.valjean;

import cn.hutool.http.HttpRequest;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GeekBang {

    public static final String url = "http://localhost:2022/geoIp/getCountryByIp?ip=127.0.0.1";

    public static void main(String[] args) throws IOException {
//        huToolCall();
//        okSyncGetCall();
        httpClientCall();

    }

    /**
     * hutool 工具合集
     * https://www.hutool.cn/docs/
     * 工作业务中，用到了， 自定义header
     */
    public static void huToolCall() {

        String respMsg = HttpRequest.get(url)
                .header("internal", "xxxxxxxxxxx")
                .execute()
                .body();
        System.out.println("respMsg = " + respMsg);

    }

    /**
     * okhttp 同步Get 请求
     * OkHttpClient#Builder构造客户端对象;
     * 构造Request对象；
     * 通过前两步中的对象构建Call对象；
     * 通过Call#execute(Callback)方法来提交异步请求；
     */
    public static void okSyncGetCall() throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        //默认就是GET请求，可以不写
        final Request request = new Request.Builder()
                .header("internal", "xxxxxxxxxxx")
                .url(url).get().build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String respMsg = response.body().string();
        System.out.println("respMsg = " + respMsg);

    }

    /**
     * 1.获得一个httpclient对象
     * 2.生成一个get请求
     * 3.执行get请求并返回结果
     */
    public static void httpClientCall() throws IOException {

        HttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.get()
                .setUri(url)
                .setHeader("internal", "xxxxxxxxxxx")
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
        HttpResponse response = client.execute(request);

        Scanner sc = new Scanner(response.getEntity().getContent());

        System.out.println(response.getStatusLine());
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }

    }


}
