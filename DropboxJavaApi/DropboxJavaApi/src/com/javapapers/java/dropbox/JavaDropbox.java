package com.javapapers.java.dropbox

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

import com.dropbox.core.DbxAccountInfo;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;

public class JavaDropbox {

	private static final String DROP_BOX_APP_KEY = "şifre";
	private static final String DROP_BOX_APP_SECRET = "şifre";
	DbxClient dbxClient;

	public DbxClient authDropbox(String dropBoxAppKey, String dropBoxAppSecret)
			throws IOException, DbxException {
		DbxAppInfo dbxAppInfo = new DbxAppInfo(dropBoxAppKey, dropBoxAppSecret);
		DbxRequestConfig dbxRequestConfig = new DbxRequestConfig(
				"JavaDropbox/1.0", Locale.getDefault().toString());
		DbxWebAuthNoRedirect dbxWebAuthNoRedirect = new DbxWebAuthNoRedirect(
				dbxRequestConfig, dbxAppInfo);
		String authorizeUrl = dbxWebAuthNoRedirect.start();
		System.out.println("1. Yetki: URL'ye gidin ve İzin Ver'i tıklayın: "
				+ authorizeUrl);
		System.out
				.println("2. Yetkilendirme Kodu: Yetkilendirme kodunu kopyalayın ve buraya girin ");
		String dropboxAuthCode = new BufferedReader(new InputStreamReader(
				System.in)).readLine().trim();
		DbxAuthFinish authFinish = dbxWebAuthNoRedirect.finish(dropboxAuthCode);
		String authAccessToken = authFinish.accessToken;
		dbxClient = new DbxClient(dbxRequestConfig, authAccessToken);
		System.out.println("Dropbox Account Name: "
				+ dbxClient.getAccountInfo().displayName);

		return dbxClient;
	}

	/* Dropbox boyutunu GB olarak döndürür */
	public long getDropboxSize() throws DbxException {
		long dropboxSize = 0;
		DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo();
		// in GB :)
		dropboxSize = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
		return dropboxSize;
//xls
	}
// Dosya yükleme
	public void uploadToDropbox(String fileName) throws DbxException,
			IOException {
		File inputFile = new File(fileName);
		FileInputStream fis = new FileInputStream(inputFile);
		try {
			DbxEntry.File uploadedFile = dbxClient.uploadFile("/" + fileName,
					DbxWriteMode.add(), inputFile.length(), fis);
			String sharedUrl = dbxClient.createShareableUrl("/" + fileName);
			System.out.println("Uploaded: " + uploadedFile.toString() + " URL "
					+ sharedUrl);
		} finally {
			fis.close();
		}
	}
// Dosya yaratma
	public void createFolder(String folderName) throws DbxException {
		dbxClient.createFolder("/" + folderName);
	}
// Dosya sıralama
	public void listDropboxFolders(String folderPath) throws DbxException {
		DbxEntry.WithChildren listing = dbxClient
				.getMetadataWithChildren(folderPath);
		System.out.println("Files List:");
		for (DbxEntry child : listing.children) {
			System.out.println("	" + child.name + ": " + child.toString());
		}
	}
// Dosya indirme
	public void downloadFromDropbox(String fileName) throws DbxException,
			IOException {
		FileOutputStream outputStream = new FileOutputStream(fileName);
		try {
			DbxEntry.File downloadedFile = dbxClient.getFile("/" + fileName,
					null, outputStream);
			System.out.println("Metadata: " + downloadedFile.toString());
		} finally {
			outputStream.close();
		}
	}

	public static void main(String[] args) throws IOException, DbxException {
		JavaDropbox javaDropbox = new JavaDropbox();
		javaDropbox.authDropbox(DROP_BOX_APP_KEY, DROP_BOX_APP_SECRET);
		System.out.println("Dropbox Size: " + javaDropbox.getDropboxSize()
				+ " GB");
		javaDropbox.uploadToDropbox("radio.png");
		javaDropbox.createFolder("Ağ güvenliği");
		javaDropbox.listDropboxFolders("/");
		javaDropbox.downloadFromDropbox("radio.png");

	}

}



//// local file belili olması şekilnde download fonksyoyu değişe bilir
String localPath = "local.txt";
OutputStream outputStream = new FileOutputStream(localPath);
FileMetadata metadata = client.files()
        .downloadBuilder("/Homework/Ağgüvenliği/dropbox.txt")
        .download(outputStream);


//amin hashemian
