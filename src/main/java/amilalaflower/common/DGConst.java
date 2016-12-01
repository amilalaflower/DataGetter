package amilalaflower.common;

public class DGConst {

    private DGConst(){
    }

    /**
     * バッチ名：データゲッタ
     */
    public final static String DATAGETTER =  "DataGetter";

    /**
     * バッチ名：データゲッタユーザ
     */
    public final static String DATAGETTERUSER = "DataGetterUser";

    /**
     * バッチ名：SQLSERVER連携
     */
    public final static String TOSQLSERVER =  "ToSQLServer";

    /**
     * ホーム
     */
    public final static String HOME = "/DataGetter/";

    /**
     * 取得対象日(当日)
     */
    public final static String TODAY = "当日";

    /**
     * 取得対象日(1日前)
     */
    public final static String ONE_DAYS = "1日前";

    /**
     * 取得対象日(2日前))
     */
    public final static String TWO_DAYS = "2日前";

    /**
     * 取得カラム名
     */
    public final static String GAMES = "総スタート";

    /**
     * リターンコード：異常終了
     */
    public final static int ABNORMALEND = 9;

    /**
     * 保存ファイル名
     */
    public final static String HTML_FILENAME = "/tmp.html";

    /**
     * 文字コード：UTF-8
     */
    public final static String ENC_UTF_8 = "UTF-8";

    /**
     * リストファイル名
     */
    public final static String LIST = "./List/ALL.csv";

    /**
     *
     */
    public final static String REGEX_HTTP = "^http://|^https://";
}
