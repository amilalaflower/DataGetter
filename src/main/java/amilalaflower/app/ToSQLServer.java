package amilalaflower.app;

import java.security.InvalidParameterException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import amilalaflower.business.ToSQLServerBusiness;
import amilalaflower.common.DGConst;

public class ToSQLServer extends Base {
    /*
     * ApplicationContext
     */
    ApplicationContext context = new FileSystemXmlApplicationContext("ApplicationContext.xml");

    /**
     * コンストラクタ
     */
    ToSQLServer() {
        super();
        super.COMMAND_NAME = DGConst.TOSQLSERVER;;
    }

    /**
     * データ連携メイン処理
     * @param args 引数
     */
    public static void main(final String[]args){
        int exitcode = new ToSQLServer().load(args);
        //プログラム終了 終了値:exitcode
        System.exit(exitcode);
    }

    /**
     * beanの実行
     * @throws Exception 例外
     */
    protected void execute(final String[]args) throws Exception{
        ToSQLServerBusiness bean =
                (ToSQLServerBusiness) context.getBean(super.COMMAND_NAME);

        bean.execute(args);
    }

    /**
     * 引数チェック
     */
    protected void checkArgs(final String[]args){

        try {
            // ホールID
            if(args[0] == null) {
                throw new InvalidParameterException("ホールIDが設定されていません");
            }
        } catch (InvalidParameterException e) {
            throw e;
        }
    }
}
