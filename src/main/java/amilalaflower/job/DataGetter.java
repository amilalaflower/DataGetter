package amilalaflower.app;

import java.security.InvalidParameterException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import amilalaflower.business.DataGetterBusiness;
import amilalaflower.common.DGConst;

/**
 * DataGetter
 * @author amilalaflower
 * @version 1.0
 */
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
        super.COMMAND_NAME = DGConst.DATAGETTER;
    }

    /**
     * データゲッタメイン処理
     * @param args 引数
     */
    public static void main(final String[]args){
        int exitcode = new DataGetter().load(args);
        System.exit(exitcode);
    }

    @Override
    protected void execute(final String[]args) throws Exception{
        DataGetterBusiness bean =
                (DataGetterBusiness) context.getBean(super.COMMAND_NAME);

        bean.execute(args);
    }

    @Override
    protected void checkArgs(final String[]args){

        try {
            // ホールID
            if(args[0] == null) {
                throw new InvalidParameterException("ホールIDが設定されていません");
            }
            // ターゲット日
            if(args[1] == null) {
            	throw new InvalidParameterException("ターゲット日が設定されていません");
            }
            
        } catch (InvalidParameterException e) {
            throw e;
        }
    }
}
