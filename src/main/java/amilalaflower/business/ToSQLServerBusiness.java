package amilalaflower.business;

import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import amilalaflower.mbg.entity.TMachineData;
import amilalaflower.mbg.entity.TMachineDataExample;
import amilalaflower.mbg.entity.WMachineData;
import amilalaflower.mbg.mapper.TMachineDataMapper;
import amilalaflower.mbg.mapper.WMachineDataMapper;

public class ToSQLServerBusiness {

    /**
     * Log4j
     */
    private Logger log = LogManager.getLogger();

    /**
     * Mapper
     */
     @Autowired
     private TMachineDataMapper mdmapper;

     /**
      * Mapper
      */
      @Autowired
     private WMachineDataMapper wmdmapper;

     /**
      * ホールID
      */
     private String hallId;

     /*
      * 行カウント
      */
     private int rowCount;

    public void execute(final String[]args) throws Exception {

        // ホールID
        hallId = args[0];

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -3);

        TMachineDataExample where = new TMachineDataExample();
        where.createCriteria()
            .andHallIdEqualTo(hallId)
            .andAcquisitionDateGreaterThanOrEqualTo(c.getTime());
        where.setOrderByClause("DATA_ID");
        List<TMachineData> machineDataList = mdmapper.selectByExample(where);

        machineDataList.stream()
            .forEach(this::insertData);

        log.info("{}件処理しました。", rowCount);
    }

    /**
     * 連携先データ登録
     * @param data
     */
    private void insertData(TMachineData data) {
        try {
            WMachineData wmd = new WMachineData();
            wmd.setDataId(data.getDataId());
            wmd.setHallId(data.getHallId());
            wmd.setAcquisitionDate(data.getAcquisitionDate());
            wmd.setMachineNo(data.getMachineNo());
            wmd.setMachineName(data.getMachineName());
            wmd.setGames(data.getGames());
            wmd.setPayout(data.getPayout());
            rowCount =+ wmdmapper.insertWorkData(wmd);
        } catch (Exception e) {
            log.error("ワークテーブルのInsertに失敗", e);
            throw e;
        }
    }
}
