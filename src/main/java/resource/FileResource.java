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

package resource;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import servicemap.ZipUtility;

@Path("/file")
@JsonSerialize
public class FileResource {

	private final String TRIPLESDIR = Paths.get("").toAbsolutePath().toString() + "/Triples";
	private final String TMPDIR = Paths.get("").toAbsolutePath().toString() + "/tmp";
	private final String FORMAT = ".n3";

	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFiles(@QueryParam("graph") String graph) throws Exception {
		File dir = new File(TRIPLESDIR + File.separator + graph);
		// Most recent folder selection
		for (int i = 0; i < 4; i++) {
			File[] files = dir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.isDirectory();
				}
			});
			Arrays.sort(files);
			dir = new File(dir.getPath() + File.separator + files[files.length - 1].getName());
		}
		File dest = new File(TMPDIR + File.separator + new Timestamp(System.currentTimeMillis()).getTime() + ".zip");
		ZipUtility.zipDirectory(dir, dest, FORMAT);

		Response response = Response.ok(FileUtils.readFileToByteArray(dest)).type("application/zip")
				.header("Content-Disposition", "attachment; filename=\"filename.zip\"").build();
		dest.delete();
		return response;
	}

}
