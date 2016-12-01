package amilalaflower.business;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;

import amilalaflower.common.DGConst;
import amilalaflower.common.DataGetterException;
import amilalaflower.common.HtmlParser;
import amilalaflower.common.MachineData;
import amilalaflower.common.Property;

public class DataGetterUserBusiness {

    /**
     * Log4j
     */
    private Logger log = LogManager.getLogger();

    /*
     * machineData
     */
    private MachineData md = new MachineData();

     /**
      * Property
      */
     @Autowired
     private Property prop;

     /**
      * トータル差枚
      */
     private int totalGames;

     /**
      * トータルゲーム数
      */
     private int totalSamai;

     // ホールURL
     private String hallurl;
     // スリープ時間
     private int sleeptime;

    /**
     * メイン処理実行
     * @throws Exception 例外
     */
    public void execute(final String[]args) throws Exception {

        totalGames = 0;
        totalSamai = 0;

        hallurl = prop.getHall_url();
        sleeptime = Integer.parseInt(prop.getSleeptime());

        try{
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date strDate = sdf.parse(sdf.format(cal.getTime()));

            md.setDate(strDate);

            // 台リスト取得
            ArrayList<String> machineList = new ArrayList<String>();
            log.info("指定した機種リストを読み込みます");
            getMachineList(machineList);

            // 台データ登録処理
            List<String> numberList = machineList.stream()
                .sequential()
                .map(Object::toString)
                .collect(Collectors.toList());

            for (final String number : numberList) {
                int cnt = 0;
                while (cnt < 3) {
                    try {
                        getMachineData(number);
                        break;
                    } catch (Exception e) {
                        cnt++;
                        Thread.sleep(sleeptime);
                    }
                }
            }

            System.out.println("トータル差枚:" + totalSamai + "  トータルゲーム数:" + totalGames);
            log.info("トータル差枚:{}  トータルゲーム数:{}", totalSamai, totalGames);

        } catch (Exception e) {
            log.error("予期せぬエラーが発生しました", e);
            throw e;
        }
    }

    /**
     * 指定機種のリストファイルを取得する処理
     * @param machineList 台リスト
     * @throws DataGetterException 例外
     */
    private void getMachineList(final ArrayList<String> machineList) throws DataGetterException{


        try {
            String listFile = DGConst.LIST;

            if(listFile == null){
                log.error("リストファイル取得に失敗しました");
                throw new DataGetterException();
            }

            log.info(listFile);

            try (BufferedReader br = new BufferedReader(new FileReader(listFile));) {
                String line = "";
                while ((line = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line, ",");

                    while (st.hasMoreTokens()) {
                        machineList.add(trimLeftZero(st.nextToken()));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            log.error("リストファイルの読み込みに失敗しました");
            throw new DataGetterException();
        } catch (IOException e) {
            log.error("リストファイルの読み込みに失敗しました");
            throw new DataGetterException();
        }
    }

    /**
     * 先頭の0を削除する処理
     * @param str 処理文字列
     * @return トリミング文字列
     */
    private String trimLeftZero(final String str) {
        return str.replaceFirst("^0+", "");
    }

    /**
     * ファイルダウンロード処理
     * @param htmlURL ダウンロードURL
     * @param saveFilePath 保存先
     * @throws DataGetterException 例外
     */
    private byte[] download(final String htmlURL) throws DataGetterException {

        StringBuilder buf = new StringBuilder();
        byte[] imageBin = null;

        try {
            URL url = new URL(htmlURL);
            URLConnection con = url.openConnection();

            try (InputStream in = con.getInputStream();
                 ByteArrayOutputStream  out = new ByteArrayOutputStream();) {

                byte[] b = new byte[4096];
                int readByte = 0;

                while(-1 != (readByte = in.read(b))) {
                    out.write(b, 0, readByte);
                }
                imageBin = out.toByteArray();
            }
        } catch (Exception e) {
            log.error("ファイルのダウンロードに失敗しました", e);
            throw new DataGetterException(e);
        }
        buf.toString();
        return imageBin;
    }

    /**
     * 差枚計算
     * @param number 台番号
     * @throws Exception 例外
     */
    private void getMachineData(final String number) throws Exception {
        try {
            log.debug("MachineNumber:{}", number);
            StringBuilder buf = new StringBuilder();
            String graphUrl = null;
            HtmlParser htmlparse = null;
            HtmlCleaner cleaner = null;
            byte[] imageBin = null;

            // 台データのHTMLページダウンロード処理
            buf.append(hallurl);
            buf.append(number);
            String machineUrl = buf.toString();
            buf.setLength(0);

            cleaner = new HtmlCleaner();
            TagNode node = cleaner.clean(new URL(machineUrl));
            htmlparse = new HtmlParser(node);
            graphUrl = htmlparse.getGraph(number);
            graphUrl = fixUrl(graphUrl);

            // グラフ画像ダウンロード処理
            imageBin = download(graphUrl);

            md.setMachineNo(number);
            md.setMachineName(htmlparse.getName(number));
            md.setSamai(readGraph(imageBin));
            md.setGames(htmlparse.getGames(number));

            System.out.println("台番号:" + md.getMachineNo() + " 機種名:" + md.getMachineName() + " ゲーム数:" + md.getGames() + " 差枚:" + md.getSamai());

            log.info("台番号:{} 機種名:{} ゲーム数:{} 差枚:{}",
                    md.getMachineNo(), md.getMachineName(), md.getGames(), md.getSamai());

            totalGames = totalGames + md.getGames();
            totalSamai = totalSamai + md.getSamai();

            Thread.sleep(sleeptime);
        } catch (Exception e) {
            log.error("台データ取得失敗  ★台番号:{}", number, e);
            throw e;
        }
    }

    /**
     * グラフ画像を読み取り座標を取得
     * @param imageBin スランプグラフイメージ
     * @throws DataGetterException 例外
     * @return samai 差枚
     */
    private int readGraph(final byte[] imageBin) throws DataGetterException {

        BufferedImage graphImage = null;
        int samai = 0;
        int color = 0;

        // プロパティファイル値取得
        // 高さチェック値
        int heightchk = Integer.parseInt(prop.getHeight());
        // 幅チェック値
        int widthachk = Integer.parseInt(prop.getWidth());
        // 閉店座標
        int closedPx = Integer.parseInt(prop.getClosed_px());
        // ターゲット色
        int targetColor = Integer.parseInt(prop.getGraph_color());

        try {

            graphImage = ImageIO.read(new ByteArrayInputStream(imageBin));

            int height = graphImage.getHeight();
            int width = graphImage.getWidth();
            // 画像サイズが正しいかチェック
            if(heightchk != height || widthachk != width) {
                throw new IOException("ファイルの形式が不正です");
            }

            outside:
            for(int x = closedPx;x > 0 ;x--) {
                for(int y = 0;y < height;y++) {
                    color = graphImage.getRGB(x,y);
                    log.trace("X:{} Y:{} getRGB:{}", x, y , color);
                    // 指定した座標の色を取得
                    if(targetColor == color) {
                        samai = getSamai(y + 2);
                        break outside;
                    }
                }
            }

            return samai;

        }catch(IOException e){
            // 読み込みもしくはデコードエラー
            log.error("スランプグラフデータ読み込み失敗", e);
            throw new DataGetterException();
        }
    }

    /**
     * 取得した座標から差枚を計算
     * @param y 取得座標
     * @return saimai 差枚
     */
    private int getSamai(final int y) {
        int samai;
        double pixel;

        // 設定ファイル値取得
        // 差枚0Y座標
        int zeroPx = Integer.parseInt(prop.getZero_px());
        // 1PXあたりのメダル
        double medalOnePx = Double.parseDouble(prop.getMedal_px());

        pixel = y - zeroPx;
        log.debug("Pixel:{}", pixel);
        samai = (int)(pixel * medalOnePx);
        samai = -samai;
        log.debug("PayOut:{}", samai);
        return samai;
    }

    /**
     * グラフURL形式修正処理
     * @param url グラフURL
     * @return fixedUrl 修正済みURL
     */
    private String fixUrl(final String url) {
        String fixedUrl = null;
        String baseUrl = prop.getUrl();
        String regex = DGConst.REGEX_HTTP;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);

        if (m.find()){
            fixedUrl = url;
        }else{
            fixedUrl = baseUrl + url;
        }

        return fixedUrl;
    }
}
