package com.stok.ramazan.pojo;

import java.util.List;

public class SatisRaporlarıDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReportType reportType;
	private List<SiparisListesiDTO> lstSiparis;
	private List<Harcama> lstHarcama;
	// Net Kar
	private double pureProfit;

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public List<SiparisListesiDTO> getLstSiparis() {
		return lstSiparis;
	}

	public void setLstSiparis(List<SiparisListesiDTO> lstSiparis) {
		this.lstSiparis = lstSiparis;
	}

	public List<Harcama> getLstHarcama() {
		return lstHarcama;
	}

	public void setLstHarcama(List<Harcama> lstHarcama) {
		this.lstHarcama = lstHarcama;
	}

	public double getPureProfit() {
		return pureProfit;
	}

	public void setPureProfit(double pureProfit) {
		this.pureProfit = pureProfit;
	}

	public enum ReportType {
		GUNLUK, HAFTALIK, AYLIK, YILLIK
	}
}
