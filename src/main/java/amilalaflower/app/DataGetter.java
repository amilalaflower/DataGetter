/**
 * DataGetter
 * @author amilalaflower
 * @version 1.0
 */
package amilalaflower.app;

import java.security.InvalidParameterException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import amilalaflower.business.DataGetterBusiness;
import amilalaflower.common.DGConst;


public class DataGetter extends Base {

    /*
     * ApplicationContext
     */
    ApplicationContext context = new FileSystemXmlApplicationContext("ApplicationContext.xml");

    /**
     * コンストラクタ
     */
    DataGetter() {
        super();
        super.COMMAND_NAME = DGConst.DATAGETTER;;
    }

    /**
     * データゲッタメイン処理
     * @param args 引数
     */
    public static void main(final String[]args){
        int exitcode = new DataGetter().load(args);
        //プログラム終了 終了値:exitcode
        System.exit(exitcode);
    }

    /**
     * beanの実行
     * @throws Exception 例外
     */
    protected void execute(final String[]args) throws Exception{
        DataGetterBusiness bean =
                (DataGetterBusiness) context.getBean(super.COMMAND_NAME);

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
