package com.mfy.test.advisor;

import lombok.Data;

@Data
public class LockProperties {

	private String lockName;

	private int expire;

	private String key;

}
