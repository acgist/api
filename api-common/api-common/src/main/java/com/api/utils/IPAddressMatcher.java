package com.api.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * IP匹配：IPv6：0:0::/112，IPv4：192.168.1.0/24
 */
public class IPAddressMatcher {

	private final int mask;
	private final InetAddress requestAddress;

	public IPAddressMatcher(String address) {
		if (address.indexOf('/') > 0) {
			String[] addressAndMask = StringUtils.split(address, "/");
			address = addressAndMask[0];
			mask = Integer.parseInt(addressAndMask[1]);
		} else {
			mask = -1;
		}
		this.requestAddress = parseAddress(address);
	}

	public boolean matches(HttpServletRequest request) {
		return matches(request.getRemoteAddr());
	}

	public boolean matches(String address) {
		InetAddress remoteAddress = parseAddress(address);
		if (mask < 0) {
			return remoteAddress.equals(requestAddress);
		}
		byte[] remoteBytes = remoteAddress.getAddress();
		byte[] requestBytes = requestAddress.getAddress();
		int oddBits = mask % 8;
		int maskBytes = mask / 8 + (oddBits == 0 ? 0 : 1);
		byte[] mask = new byte[maskBytes];
		Arrays.fill(mask, 0, oddBits == 0 ? mask.length : mask.length - 1, (byte) 0xFF);
		if (oddBits != 0) {
			int finalByte = (1 << oddBits) - 1;
			finalByte <<= 8 - oddBits;
			mask[mask.length - 1] = (byte) finalByte;
		}
		for (int i = 0; i < mask.length; i++) {
			if ((remoteBytes[i] & mask[i]) != (requestBytes[i] & mask[i])) {
				return false;
			}
		}
		return true;
	}

	private InetAddress parseAddress(String address) {
		try {
			return InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("IP地址错误" + address, e);
		}
	}

}
