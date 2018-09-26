package com.tfb.project.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "t_profit_day_new")
public class TProfitDayNew {
    /**
     * 一级业务类型(1-代扣 2-代付 3-聚合4-网关 5-快捷 6-实名认证 7 银联h5 8 同名支付)
     */
    @Column(name = "Fbuss_type")
    private String fbussType;

    /**
     * 二级业务类型(acp-普通代扣 verify_acp-鉴权代扣 pay-普通代付（余额）D0pay-垫资代付)
     */
    @Column(name = "Fbuss_type2")
    private String fbussType2;

    /**
     * 交易成功金额
     */
    @Column(name = "Ftran_total")
    private Long ftranTotal;

    /**
     * 交易成功笔数
     */
    @Column(name = "Ftran_count")
    private Long ftranCount;

    /**
     * 交易金额（参与分润的金额）
     */
    @Column(name = "Ftran_amt")
    private Long ftranAmt;

    /**
     * 交易笔数（参与分润的笔数）
     */
    @Column(name = "Ftran_cnt")
    private Integer ftranCnt;

    /**
     * 交易商户号
     */
    @Column(name = "Ftrade_spid")
    private String ftradeSpid;

    /**
     * 商户百分比费率
     */
    @Column(name = "Ftrade_rate")
    private Double ftradeRate;

    /**
     * 利润
     */
    @Column(name = "Fincome")
    private Long fincome;

    /**
     * 交易商户名称
     */
    @Column(name = "Ftrade_spid_name")
    private String ftradeSpidName;

    /**
     * 代理商商户号
     */
    @Column(name = "Fspid")
    private String fspid;

    /**
     * 代理商百分比费率
     */
    @Column(name = "Fsp_rate")
    private Double fspRate;

    /**
     * 代理商商户名称
     */
    @Column(name = "Fspid_name")
    private String fspidName;

    /**
     * 代理商商户成本
     */
    @Column(name = "Fspid_cost")
    private String fspidCost;

    /**
     * 下级商户号
     */
    @Column(name = "Fsub_spid")
    private String fsubSpid;

    /**
     * 下级商户名称
     */
    @Column(name = "Fsub_spid_name")
    private String fsubSpidName;

    /**
     * 下级商户成本
     */
    @Column(name = "Fsub_spid_cost")
    private String fsubSpidCost;

    /**
     * 二级代理商百分比费率
     */
    @Column(name = "Fsub_rate")
    private Double fsubRate;

    /**
     * 代理商商户分润金额
     */
    @Column(name = "Fspid_profit")
    private Long fspidProfit;

    /**
     * 分润日期
     */
    @Column(name = "Fdate")
    private Date fdate;

    /**
     * 二级代理商分润
     */
    @Column(name = "Fsub_spid_profit")
    private Long fsubSpidProfit;
}