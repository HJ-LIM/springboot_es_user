package com.simple.juni.security.domain.bean;

import com.simple.juni.excel.ExcelColumn;
import com.simple.juni.excel.style.BlueHeaderStyle;
import com.simple.juni.excel.style.ExcelColumnStyle;
import com.simple.juni.security.domain.SimplePrint;
import com.simple.juni.security.domain.enums.CBLType;
import com.simple.juni.security.domain.enums.ClientType;
import com.simple.juni.security.domain.enums.ElectricChargeType;
import com.simple.juni.security.domain.enums.FetchType;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class User extends SimplePrint {
	@ExcelColumn(
		order = 0,
		headerName = "ID",
		headerStyle = @ExcelColumnStyle(excelCellStyleClass = BlueHeaderStyle.class)
	)
	private @NonNull String _id;
	@ExcelColumn(order = 1, headerName = "이름")
	private String companyName;								//참여고객 명
	@ExcelColumn(order = 2, headerName = "고객번호")
	private String custNo;									// 고객번호 == 공급지점 특정번호
	private String custNoPassword;							// 고객번호 비밀번호
	private String rId; 									// gateway sequential no
	private ClientType clientType = ClientType.NONE;		// gateway client type
	@ExcelColumn(order = 3, headerName = "자원ID")
	private String dResourceId;								// demand resource Id
	private String dResourceGroupId;
	private boolean economicDR;								// Verifies the existence of a economic DR
	private boolean reliableDR;								// Verifies the existence of a reliable DR
															// Electric charge type
	private ElectricChargeType chargeType = ElectricChargeType.BUSINESS;
	private CBLType cblType = CBLType.MAX;				// CBL based load type
	private boolean cblSaa = true;							// Verifies the existence of a CBL SAA
	private String timeZone;
	private String timeZoneDisplay;
	private long created = System.currentTimeMillis();
	private long modified = System.currentTimeMillis();
	private long registeredInAgent = System.currentTimeMillis();
	private long registeredInDemand = System.currentTimeMillis();
	private boolean isDeleted = false;						// 중지여부
	private boolean isIncludedSettlement = true;			// 정산 참여 여부
	private FetchType fetchType = FetchType.KEPCO_API;
	private int contractIng;								// contractIng
	private String numberOfSPI;								//공급지점 특정번호

	//Contract
	private long rrmse;										//
	private float rrmseValue;								// RRMSE float(2019.08.06 실수형으로 변경)
	private float rrmseMax;
	private float rrmseNoAdjusted;
	private float rrmse30days;
	private float rrmseOnlyTheDay;
	private long contract_active_period;					// time to reduction
	@ExcelColumn(order = 4, headerName = "계약 용량")
	private double capacity;								// 계약용량
	@ExcelColumn(order = 5, headerName = "계약 시작")
	private long contractFrom;								// 계약 시작
	@ExcelColumn(order = 6, headerName = "계약 종료")
	private long contractTo;								// 계약 종료
	private long endowmentRate;								// 기본금
	private long ePCRate; 									// 경제성 실적금 분배율
	private long rPCRate; 									// 신뢰성 실적금 분배율
	private long commission;								// 추가 수수료율
	private String bank; 									// bank
	private String account; 								// account number
	private float rpNFFactor = 2;							// 신뢰성 DR 미이행 위약금 계수 - not fulfill
	private float epNFFactor;								// 경제성 DR 미이행 위약금 계수
	private long ePRate; 									// 경제성 위약금 (penalty) 분배율
	private long rPRate; 									// 신뢰성 위약금 (penalty) 분배율
	private boolean isYearContract = false; 				// determines whether 1 year contract or 6 month contract
	private double contractPrice;							// 계약단가
	private double contractUnitPrice;						// 종량단가
	private double adjustmentPrice;							// 조정단가
	private int stopPlanDays;								// 년간정지계획일수
	private int otherStopPlanDays;							// 년간정지외계획일수
	private int availableHours;								// 제공가능시간수
	private int contractType;								// 계약타입
	private String businessCode;
	private String cidr;									// 허용 IP 주소 (CIDR)
	private String subLoginId;								// 추가 로그인 ID
	private int errorCount = 1;								// 로그인 실패 횟수
}
