package com.android.acadgild.movieapp192.network;


import com.android.acadgild.movieapp192.utils.CommonUtilities;

/**
 * @author Preetika
 *
 */
public interface OnWebServiceResult {
	public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type);
}
