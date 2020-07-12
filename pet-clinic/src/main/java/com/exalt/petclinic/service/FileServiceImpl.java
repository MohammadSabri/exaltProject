package com.exalt.petclinic.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

import com.exalt.petclinic.exception.CommonException;
import com.exalt.petclinic.exception.ErrorEnum;

@Service

public class FileServiceImpl implements FileService {

	@Override
	public String create(String fileName) {
		String fileDir = Paths.get("").toAbsolutePath().toString() + "\\" + fileName;
		File file = new File(fileDir);
		if (file.exists())
			return "File already exist in  " + fileDir;
		else {
			try {
				file.createNewFile();
				return "file created successfuly with the current path:" + fileDir;
			} catch (IOException e) {
				return "can not creat the file";
			}
		}
	}

	@Override
	public String update(String data, String fileName) {
		String fileDir = Paths.get("").toAbsolutePath().toString() + "\\" + fileName;
		FileWriter myWriter;
		File file = new File(fileDir);
		if (!file.exists()) {
			throw new CommonException(ErrorEnum.FILE_NOT_FOUND);
		}
		try {
			myWriter = new FileWriter(fileDir);

			myWriter.write(data);
			myWriter.close();
			return "sucsessful write to file ";
		} catch (IOException e) {
			throw new CommonException(ErrorEnum.CAN_NOT_WRITE_TO_FILE);
		}
	}

	@Override
	public String get(String fileName) {
		String fileDir = Paths.get("").toAbsolutePath().toString() + "\\" + fileName;
		File file = new File(fileDir);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			try {
				fis.read(data);
				fis.close();
			} catch (IOException e) {
				throw new CommonException(ErrorEnum.CAN_NOT_READ_FILE);
			}
			try {
				String str = new String(data, "UTF-8");
				return str;
			} catch (UnsupportedEncodingException e) {
				throw new CommonException(ErrorEnum.UNSUPORTED_ENCODING);
			}

		} catch (FileNotFoundException e) {

			throw new CommonException(ErrorEnum.FILE_NOT_FOUND, e);

		}

	}

	@Override
	public List<String> getAll(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String fileName) {
		// TODO Auto-generated method stub

	}

}
