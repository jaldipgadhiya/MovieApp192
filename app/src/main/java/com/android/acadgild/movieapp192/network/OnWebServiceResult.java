package com.android.acadgild.movieapp192.network;


import com.android.acadgild.movieapp192.utils.CommonUtilities;

/**
 * Created by Jal on 22-10-2017.
 *
 */
public interface OnWebServiceResult {
	public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type);
}
