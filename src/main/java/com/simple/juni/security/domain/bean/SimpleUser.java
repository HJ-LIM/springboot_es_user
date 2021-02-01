package com.simple.juni.security.domain.bean;

import com.simple.juni.security.domain.enums.CBLType;
import com.simple.juni.security.domain.enums.ClientType;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SimpleUser extends User {
	private @NonNull String _id;
	private String companyName;								//참여고객 명
	private String custNo;									// 고객번호 == 공급지점 특정번호
	private String rId; 									// gateway sequential no
	private ClientType clientType = ClientType.NONE;		// gateway client type
	private String dResourceId;								// demand resource Id
	private CBLType cblType = CBLType.MAX;				// CBL based load type
	private long created = System.currentTimeMillis();
	private String numberOfSPI;								//공급지점 특정번호
	private double capacity;								// 계약용량
	private long contractFrom;								// 계약 시작
	private long contractTo;								// 계약 종료

	public SimpleUser(@NonNull String _id) {
		super(_id);
	}
}
