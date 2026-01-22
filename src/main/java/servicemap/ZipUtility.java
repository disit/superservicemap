/* SuperServiceMap.
   Copyright (C) 2015 DISIT Lab http://www.disit.org - University of Florence
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.
   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>. */

package servicemap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize
public class ZipUtility {

	public static void zipDirectory(File sourceDir, File zippedDir, String format) {
		byte[] buffer = new byte[1024];
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zippedDir);
			zos = new ZipOutputStream(fos);
			FileInputStream in = null;
			for (File file : sourceDir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(format);
				}})) {
				ZipEntry ze = new ZipEntry(file.getName());
				zos.putNextEntry(ze);
				try {
					in = new FileInputStream(sourceDir.getPath() + File.separator + file.getName());
					int len;
					while ((len = in .read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
				} finally {
					in.close();
				}
			}
			zos.closeEntry();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void unzipDirectory(File zippedDir, File destDir) throws IOException {
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		File canonicalDestDir = destDir.getCanonicalFile();
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zippedDir));
		ZipEntry entry = zipIn.getNextEntry();
		while (entry != null) {
			File entryTarget = new File(destDir, entry.getName());
			File canonicalTarget = entryTarget.getCanonicalFile();
			if (!canonicalTarget.getPath().startsWith(canonicalDestDir.getPath() + File.separator)) {
				throw new IOException("Zip entry outside target dir: " + entry.getName());
			}
			if (!entry.isDirectory()) {
				extractFile(zipIn, canonicalTarget);
			} else {
				canonicalTarget.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}

	private static void extractFile(ZipInputStream zipIn, File targetFile) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile));
		byte[] bytesIn = new byte[4096];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

}
