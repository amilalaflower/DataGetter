/**
 * DataGetter
 * @author amilalaflower
 * @version 1.0
 */
package amilalaflower.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import amilalaflower.business.DataGetterUserBusiness;
import amilalaflower.common.DGConst;


public class DataGetterUser extends Base {

    /*
     * ApplicationContext
     */
    ApplicationContext context = new FileSystemXmlApplicationContext("ApplicationContext.xml");

    /**
     * コンストラクタ
     */
    DataGetterUser() {
        super();
        super.COMMAND_NAME = DGConst.DATAGETTERUSER;;
    }

    /**
     * データゲッタメイン処理
     * @param args 引数
     */
    public static void main(final String[]args){
        int exitcode = new DataGetterUser().load(args);
        //プログラム終了 終了値:exitcode
        System.exit(exitcode);
    }

    /**
     * beanの実行
     * @throws Exception 例外
     */
    protected void execute(final String[]args) throws Exception{
        DataGetterUserBusiness bean =
                (DataGetterUserBusiness) context.getBean(super.COMMAND_NAME);

        bean.execute(args);
    }

    /**
     * 引数チェック
     */
    protected void checkArgs(final String[]args){
    }
}
