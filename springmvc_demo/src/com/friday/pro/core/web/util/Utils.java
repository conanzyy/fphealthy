package com.friday.pro.core.web.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Utils {
	public static String getUUID() {
		String uuid = "";
		uuid = java.util.UUID.randomUUID().toString();
		return uuid;
	}

	public static void main(String[] args) {
		System.out.println(Utils.getUUID());
	}
}
