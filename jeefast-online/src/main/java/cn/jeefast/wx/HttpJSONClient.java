package cn.jeefast.wx;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *  * @java后台获取json数据 工具类  * @date:2013-11-11 下午6:12:26    
 */
public class HttpJSONClient {

	/**
	 *  @param args
	 */

	public static String loadJSON(String url) {
		String str = "";
		try {
			if (!url.contains("&lang=")) {
				if (url.indexOf("?") > 1) {
					url = url + "&lang=zh_CN";
				} else {
					url = url + "?lang=zh_CN";
				}
			}
			URL oracle = new URL(url);

			URLConnection yc = oracle.openConnection();
			InputStream inputStream = yc.getInputStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			int len = 0;
			byte[] data = new byte[1024];
			while ((len = inputStream.read(data)) != -1) {

				outputStream.write(data, 0, len);
			}
			// 到这里加个UTF-8防止乱码了
			str = new String(outputStream.toByteArray(), "UTF-8");
			System.out.println("调用url：" + url);
			System.out.println("url返回的内容：" + str);
			// str = IOUtils.toString(yc.getInputStream(), "UTF-8");//这个测试还是会乱码
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return str;
	}

	/**
	 * 发送HttpPost请求
	 *
	 * @param strURL
	 *            服务地址
	 * @param params
	 *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
	 * @return 成功:返回json字符串<br/>
	 */
	public static String post(String strURL, String params) {

		try {
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(params);
			out.flush();
			out.close();
			// 读取响应
			int length = (int) connection.getContentLength();// 获取长度
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String result = new String(data, "UTF-8"); // utf-8编码

				return result;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return "error"; // 自定义错误信息
	}

}