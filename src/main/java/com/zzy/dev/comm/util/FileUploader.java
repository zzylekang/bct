package com.zzy.dev.comm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.IOFileUploadException;
import org.apache.commons.fileupload.FileUploadBase.InvalidContentTypeException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;



public class FileUploader {

	/** �������ļ��ϴ��ܴ�С�� Size Max ���� */
	public static final long NO_LIMIT_SIZE_MAX		= -1;
	/** �������ļ��ϴ������ļ���С�� File Size Max ���� */
	public static final long NO_LIMIT_FILE_SIZE_MAX	= -1;
	/** Ĭ�ϵ�д�ļ���ֵ */
	public static final int DEFAULT_SIZE_THRESHOLD	= DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD;
	/** Ĭ�ϵ��ļ��������� */
	public static final FileNameGenerator DEFAULT_FILE_NAME_GENERATOR = new CommonFileNameGenerator();
	
	private String savePath;
	private long sizeMax						= NO_LIMIT_SIZE_MAX;
	private long fileSizeMax					= NO_LIMIT_FILE_SIZE_MAX;
	private Set<String> acceptTypes				= new LStrSet();
	private Map<String, String[]> paramFields	= new HashMap<String, String[]>();
	private Map<String, FileInfo[]> fileFields	= new HashMap<String, FileInfo[]>();
	
	private FileNameGenerator fileNameGenerator	= DEFAULT_FILE_NAME_GENERATOR;
	
	private int factorySizeThreshold			= DEFAULT_SIZE_THRESHOLD;
	private String factoryRepository;
	private FileCleaningTracker factoryCleaningTracker;
	private String servletHeaderencoding;
	private ProgressListener servletProgressListener;
	
	private Throwable cause;
	
	public FileUploader()
	{
		
	}
	
	/** ���캯��
	 * 
	 * @param savePath		: �ϴ��ļ��ı���·�����������ļ��������ο���{@link FileUploader#setSavePath(String)}
	 */
	public FileUploader(String savePath)
	{
		this(savePath, null);
	}
	
	/** ���캯��
	 * 
	 * @param savePath		: �ϴ��ļ��ı���·�����������ļ��������ο���{@link FileUploader#setSavePath(String)}
	 * @param sizeMax		: �ļ��ϴ������ļ���С���ƣ�Ĭ�ϣ�{@link FileUploader#NO_LIMIT_SIZE_MAX}
	 * @param fileSizeMax	: �ļ��ϴ��ĵ����ļ���С���ƣ�Ĭ�ϣ�{@link FileUploader#NO_LIMIT_FILE_SIZE_MAX}
	 */
	public FileUploader(String savePath, long sizeMax, long fileSizeMax)
	{
		this(savePath, null, sizeMax, fileSizeMax);
	}

	/** ���캯��
	 * 
	 * @param savePath		: �ϴ��ļ��ı���·�����������ļ��������ο���{@link FileUploader#setSavePath(String)}
	 * @param acceptTypes	: �ɽ��ܵ��ϴ��ļ����ͼ��ϣ�Ĭ�ϣ�������
	 */
	public FileUploader(String savePath, String[] acceptTypes)
	{
		this(savePath, acceptTypes, NO_LIMIT_SIZE_MAX, NO_LIMIT_FILE_SIZE_MAX);
	}
	
	/** ���캯��
	 * 
	 * @param savePath		: �ϴ��ļ��ı���·�����������ļ��������ο���{@link FileUploader#setSavePath(String)}
	 * @param acceptTypes	: �ɽ��ܵ��ϴ��ļ����ͼ��ϣ�Ĭ�ϣ�������
	 * @param sizeMax		: �ļ��ϴ������ļ���С���ƣ�Ĭ�ϣ�{@link FileUploader#NO_LIMIT_SIZE_MAX}
	 * @param fileSizeMax	: �ļ��ϴ��ĵ����ļ���С���ƣ�Ĭ�ϣ�{@link FileUploader#NO_LIMIT_FILE_SIZE_MAX}
	 */
	public FileUploader(String savePath, String[] acceptTypes, long sizeMax, long fileSizeMax)
	{
		this.savePath		= savePath;
		this.sizeMax		= sizeMax;
		this.fileSizeMax	= fileSizeMax;
		
		if(acceptTypes != null)
			setAcceptTypes(acceptTypes);
	}
	
	/** ��ȡ�ϴ��ļ��ı���·�����������ļ�����*/
	public String getSavePath()
	{
		return savePath;
	}

	/** �����ϴ��ļ��ı���·�����������ļ�����
	 * 
	 * @param savePath	: �ļ�·���������Ǿ���·�������·��<br>
	 * 						1) ����·�����Ը�Ŀ¼����ʼ���磺'/'��'D:\'�����Ƿ������ļ�ϵͳ��·��<br>
	 * 						2) ���·�������Ը�Ŀ¼����ʼ��������� WEB Ӧ�ó��� Context ��·�������磺mydir ��ָ
	 * 							'${WEB-APP-DIR}/mydir'��<br>
	 * 						3) �����ϴ��ļ�ǰ�����·���Ƿ���ڣ������������᳢�����ɸ�·�����������ʧ����
	 * 						   �ϴ�ʧ�ܲ����� {@link Result#INVALID_SAVE_PATH}
	 * 
	 */
	public void setSavePath(String savePath)
	{
		this.savePath = savePath;
	}

	/** ��ȡ�ļ��ϴ��ĵ����ļ���С���� */
	public long getFileSizeMax()
	{
		return fileSizeMax;
	}

	/** �����ļ��ϴ��ĵ����ļ���С���� */
	public void setFileSizeMax(long fileSizeMax)
	{
		this.fileSizeMax = fileSizeMax;
	}

	/** ��ȡ�ļ��ϴ������ļ���С���� */
	public long getSizeMax()
	{
		return sizeMax;
	}

	/** �����ļ��ϴ������ļ���С���� */
	public void setSizeMax(long sizeMax)
	{
		this.sizeMax = sizeMax;
	}

	/** ��ȡ�ɽ��ܵ��ϴ��ļ����ͼ��� */
	public Set<String> getAcceptTypes()
	{
		return acceptTypes;
	}

	/** ���ÿɽ��ܵ��ϴ��ļ����ͼ��� */
	public void setAcceptTypes(Set<String> acceptTypes)
	{
		this.acceptTypes.clear();
		
		for(String type : acceptTypes)
			addAcceptType(type);
	}

	/** ���ÿɽ��ܵ��ϴ��ļ����ͼ��� */
	public void setAcceptTypes(String[] acceptTypes)
	{
		this.acceptTypes.clear();
		
		for(String type : acceptTypes)
			addAcceptType(type);
	}
	
	/** ���һ���ɽ��ܵ��ϴ��ļ����� */
	public boolean addAcceptType(String acceptType)
	{
		acceptType = adjustAcceptType(acceptType);
		
		if(acceptType.length() > 1)
			return this.acceptTypes.add(acceptType);
		
		return false;
	}

	/** ɾ��һ���ɽ��ܵ��ϴ��ļ����� */
	public boolean removeAcceptType(String acceptType)
	{
		acceptType = adjustAcceptType(acceptType);
		
		if(acceptType.length() > 1)
			return this.acceptTypes.remove(acceptType);
		
		return false;
	}

	private String adjustAcceptType(String acceptType)
	{
		int index = acceptType.lastIndexOf(".");
		
		if(index != -1)
			acceptType = acceptType.substring(index, acceptType.length());
		else
			acceptType = "." + acceptType;
		
		return acceptType;
	}

	/** ��ȡ���з��ļ������ӳ�� 
	 * 
	 * @return 	: key -> �������ƣ�value ->  �����ݣ�����Ϊ {@link String}[ ]
	 * 
	 */
	public Map<String, String[]> getParamFields()
	{
		return paramFields;
	}
	
	private void addParamField(String name, String value)
	{
		String[] array = paramFields.get(name);
		array = addField(array, name, value);
		paramFields.put(name, array);
	}
	
	@SuppressWarnings("unchecked")
	private <T> T[] addField(T[] array, String name, T value)
	{
		if(array == null)
		{
			array = (T[])Array.newInstance(value.getClass(), 1);
			array[0] = value;
		}
		else
		{
			T[] array2 = (T[])Array.newInstance(value.getClass(), array.length + 1);
			System.arraycopy(array, 0, array2, 0, array.length);
			array2[array.length] = value;
			array = array2;
		}
		
		return array;
	}

	/** ��ȡ�����ļ������ӳ�� 
	 * 
	 * @return 	: key -> �������ƣ�value ->  {@link FileInfo}[ ]
	 * 
	 */
	public Map<String, FileInfo[]> getFileFields()
	{
		return fileFields;
	}
	
	private void addFileField(String name, FileInfo value)
	{
		FileInfo[] array = fileFields.get(name);
		array = addField(array, name, value);
		fileFields.put(name, array);
	}

	/** ��ȡ�ϴ��ļ������е���ʱ�ļ����λ�ã��ο���{@link DiskFileItemFactory#getRepository()} */
	public String getFactoryRepository()
	{
		return factoryRepository;
	}

	/** �����ϴ��ļ������е���ʱ�ļ����λ�ã��ο���{@link DiskFileItemFactory#setRepository(File)} */
	public void setFactoryRepository(String factoryRepository)
	{
		this.factoryRepository = factoryRepository;
	}

	/** ��ȡд�ļ���ֵ�����ϴ������ݳ�����ֵ�Ͱ�����д�������У��ο���{@link DiskFileItemFactory#getSizeThreshold()} */
	public int getFactorySizeThreshold()
	{
		return factorySizeThreshold;
	}

	/** ����д�ļ���ֵ�����ϴ������ݳ�����ֵ�Ͱ�����д�������У��ο���{@link DiskFileItemFactory#setSizeThreshold(int)} */
	public void setFactorySizeThreshold(int factorySizeThreshold)
	{
		this.factorySizeThreshold = factorySizeThreshold;
	}

	/** ��ȡ��ʱ�ļ����������ο���{@link DiskFileItemFactory#getFileCleaningTracker()} */
	public FileCleaningTracker getFactoryCleaningTracker()
	{
		return factoryCleaningTracker;
	}

	/** ������ʱ�ļ����������ο���{@link DiskFileItemFactory#setFileCleaningTracker(FileCleaningTracker)} */
	public void setFactoryCleaningTracker(FileCleaningTracker factoryCleaningTracker)
	{
		this.factoryCleaningTracker = factoryCleaningTracker;
	}

	/** ��ȡ�ϴ���������ϴ����ݵı����ʽ���ο���{@link ServletFileUpload#getHeaderEncoding()} */
	public String getServletHeaderencoding()
	{
		return servletHeaderencoding;
	}

	/** �����ϴ���������ϴ����ݵı����ʽ���ο���{@link ServletFileUpload#setHeaderEncoding(String)} */
	public void setServletHeaderencoding(String servletHeaderencoding)
	{
		this.servletHeaderencoding = servletHeaderencoding;
	}

	/** ��ȡ�ϴ�����Ĵ�����̼��������ο���{@link ServletFileUpload#getProgressListener()} */
	public ProgressListener getServletProgressListener()
	{
		return servletProgressListener;
	}

	/** �����ϴ�����Ĵ�����̼��������ο���{@link ServletFileUpload#setProgressListener(ProgressListener)} */
	public void setServletProgressListener(ProgressListener servletProgressListener)
	{
		this.servletProgressListener = servletProgressListener;
	}
	
	/** ��ȡ�ļ������������ο���{@link FileNameGenerator} */
	public FileNameGenerator getFileNameGenerator()
	{
		return fileNameGenerator;
	}

	/** �����ļ������������ο���{@link FileNameGenerator} */
	public void setFileNameGenerator(FileNameGenerator fileNameGenerator)
	{
		this.fileNameGenerator = fileNameGenerator;
	}

	/** ��ȡ�ļ��ϴ�ʧ�ܵ�ԭ���ļ��ϴ�ʧ��ʱʹ�ã� */
	public Throwable getCause()
	{
		return cause;
	}
	
	private void reset()
	{
		cause = null;
		fileFields.clear();
		paramFields.clear();
	}

	private void clean(List<FileItemInfo> fiis, int count)
	{
		reset();
		
		for(int i = 0; i < count; i++)
		{
			File file = fiis.get(i).file;
			
			try
			{
				file.delete();
			}
			catch(SecurityException e)
			{
				
			}
		}
	}

	/** ִ���ϴ�
	 * 
	 * @param request	: {@link HttpServletRequest} ����
	 * @param response	: {@link HttpServletResponse} ����
	 * 
	 * @return			: �ɹ������� {@link Result#SUCCESS} ��ʧ�ܣ��������������
	 * 					  ʧ��ԭ��ͨ�� {@link FileUploader#getCause()} ��ȡ
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Result upload(HttpServletRequest request, HttpServletResponse response)
	{
		reset();

		String absolutePath	 = getAbsoluteSavePath(request);
		if(absolutePath == null)
		{
			cause = new FileNotFoundException(String.format("path '%s' not found or is not directory", savePath));
			return Result.INVALID_SAVE_PATH;
		}
		
		ServletFileUpload sfu	= getFileUploadComponent();
		List<FileItemInfo> fiis	= new ArrayList<FileItemInfo>();
		
		List<FileItem> items	= null;
		Result result			= Result.SUCCESS;
		
		String encoding					= servletHeaderencoding != null ? servletHeaderencoding : request.getCharacterEncoding();
		FileNameGenerator fnGenerator	= fileNameGenerator != null ? fileNameGenerator : DEFAULT_FILE_NAME_GENERATOR;
		
		try
		{
			items = (List<FileItem>)sfu.parseRequest(request);
		}
		catch (FileUploadException e)
		{
			cause = e;
			
			if(e instanceof FileSizeLimitExceededException)		result = Result.FILE_SIZE_EXCEEDED;
			else if(e instanceof SizeLimitExceededException)	result = Result.SIZE_EXCEEDED;
			else if(e instanceof InvalidContentTypeException)	result = Result.INVALID_CONTENT_TYPE;
			else if(e instanceof IOFileUploadException)			result = Result.FILE_UPLOAD_IO_EXCEPTION;
			else												result = Result.OTHER_PARSE_REQUEST_EXCEPTION;
		}
		
		if(result == Result.SUCCESS)
		{
			result = parseFileItems(items, fnGenerator, absolutePath, encoding, fiis);	
			if(result == Result.SUCCESS)
				result = writeFiles(fiis);
		}
		
		return result;
	}

	private Result writeFiles(List<FileItemInfo> fiis)
	{
		for(int i = 0; i < fiis.size(); i++)
		{
			FileItemInfo fii = fiis.get(i);
			
			try
			{
				fii.item.write(fii.file);
			}
			catch(Exception e)
			{
				clean(fiis, i);
				
				cause = e;
				return Result.WRITE_FILE_FAIL;		
			}
		}
		
		return Result.SUCCESS;
	}

	private Result parseFileItems(List<FileItem> items, FileNameGenerator fnGenerator, String absolutePath, String encoding, List<FileItemInfo> fiis)
	{
		for(FileItem item : items)
		{
			if(item.isFormField())
				parseFormField(item, encoding);
			else
			{
				if(GeneralHelper.isStrEmpty(item.getName()))
					continue;
				
				Result result = parseFileField(item, absolutePath, fnGenerator, fiis);
				if(result != Result.SUCCESS)
				{
					reset();
					
					cause = new InvalidParameterException(String.format("file '%s' not accepted", item.getName()));
					return result;
				}
			}
		}
		
		return Result.SUCCESS;
	}

	private Result parseFileField(FileItem item, String absolutePath, FileNameGenerator fnGenerator, List<FileItemInfo> fiis)
	{
		String suffix			= null;
		String uploadFileName	= item.getName();
		boolean isAcceptType	= acceptTypes.isEmpty();
		int stuffPos			= uploadFileName.lastIndexOf(".");
		
		if(stuffPos != -1)
		{
			suffix = uploadFileName.substring(stuffPos, uploadFileName.length()).toLowerCase();
			
			if(!isAcceptType)
				isAcceptType = acceptTypes.contains(suffix);
		}
		
		if(!isAcceptType)
			return Result.INVALID_FILE_TYPE;
		
		String saveFileName = fnGenerator.generate(item, suffix);
		
		if(suffix != null && !saveFileName.endsWith(suffix))
			saveFileName += suffix;
		
		String fullFileName	= absolutePath + File.separator + saveFileName;
		File saveFile		= new File(fullFileName);
		FileInfo info		= new FileInfo(uploadFileName, saveFile);
		
		fiis.add(new FileItemInfo(item, saveFile));
		addFileField(item.getFieldName(), info);
		
		return Result.SUCCESS;
	}

	private void parseFormField(FileItem item, String encoding)
	{
		String name = item.getFieldName();
		String value = item.getString();
		
		if(!GeneralHelper.isStrEmpty(value) && encoding != null)
		{
			try
			{
				value = new String(value.getBytes("ISO-8859-1"), encoding);
			}
			catch(UnsupportedEncodingException e)
			{
	
			}
		}
		
		addParamField(name, value);
	}

	private ServletFileUpload getFileUploadComponent()
	{
		DiskFileItemFactory dif	= new DiskFileItemFactory();
		
		if(factorySizeThreshold != DEFAULT_SIZE_THRESHOLD)
			dif.setSizeThreshold(factorySizeThreshold);
		if(factoryRepository != null)
			dif.setRepository(new File(factoryRepository));
		if(factoryCleaningTracker != null)
			dif.setFileCleaningTracker(factoryCleaningTracker);
		
		ServletFileUpload sfu	= new ServletFileUpload(dif);
		
		if(sizeMax != NO_LIMIT_SIZE_MAX)
			sfu.setSizeMax(sizeMax);
		if(fileSizeMax != NO_LIMIT_FILE_SIZE_MAX)
			sfu.setFileSizeMax(fileSizeMax);
		if(servletHeaderencoding != null)
			sfu.setHeaderEncoding(servletHeaderencoding);
		if(servletProgressListener != null)
			sfu.setProgressListener(servletProgressListener);
		
		return sfu;
	}
	
	private String getAbsoluteSavePath(HttpServletRequest request)
	{
		String path	= null;
		File file	= new File(savePath);
		
		if(!file.isAbsolute())
			file = new File(HttpHelper.getRequestRealPath(request, savePath));
		if(file.isDirectory())
			path = file.getAbsolutePath();
		else if(!file.exists())
		{
			try
			{
				synchronized(FileUploader.class)
				{
					if(file.exists() || file.mkdirs())
						path = file.getAbsolutePath();
				}
			}
			catch(SecurityException e)
			{
				
			}
		}

		return path;
	}

	/** �ļ����������ӿ�
	 * 
	 * ÿ�α���һ���ϴ��ļ�ǰ����Ҫ���øýӿڵ� {@link FileNameGenerator#generate} ��������Ҫ������ļ���
	 *  
	 */
	public static interface FileNameGenerator
	{
		/** �ļ������ɷ���
		 * 
		 * @param item		: �ϴ��ļ���Ӧ�� {@link FileItem} ����
		 * @param suffix	: �ϴ��ļ��ĺ�׺��
		 * 
		 */
		String generate(FileItem item, String suffix);
	}
	
	/** ͨ���ļ���������
	 * 
	 * ʵ�� {@link FileNameGenerator} �ӿڣ���������ֵ��ʱ������Ψһ�ļ���
	 *  
	 */
	public static class CommonFileNameGenerator implements FileNameGenerator
	{
		private static final int MAX_SERIAL			= 999999;
		private static final AtomicInteger atomic = new AtomicInteger();
		
		private static int getNextInteger()
		{
			int value = atomic.incrementAndGet();
			if(value >= MAX_SERIAL)
				atomic.set(0);
			
			return value;
		}
		
		/** ��������ֵ��ʱ������ 'XXXXXX_YYYYYYYYYYYYY' ��ʽ��Ψһ�ļ��� */
		public String generate(FileItem item, String suffix)
		{
			int serial		= getNextInteger();
			long millsec	= System.currentTimeMillis();

			return String.format("%06d_%013d", serial, millsec);
		}
	}
	
	/** �ϴ��ļ���Ϣ�ṹ�� */
	public static class FileInfo
	{
		private String uploadFileName;
		private File saveFile;
		
		FileInfo()
		{
			
		}
		
		FileInfo(String uploadFileName, File saveFile)
		{
			this.uploadFileName	= uploadFileName;
			this.saveFile		= saveFile;
		}
		
		/** ��ȡ�ϴ��ļ���ԭʼ�ļ���
		 * 
		 * �����ڲ�ͬ�Ŀͻ�������������ܰ���Ҳ���ܲ������ļ�·���� 
		 * 
		 */
		public String getUploadFileName()
		{
			return uploadFileName;
		}
		
		/** ��ȡ�ϴ��ļ���ԭʼ�ļ������������ļ�·���� */
		public String getUploadFileSimpleName()
		{
			if(uploadFileName != null)
			{
				String path = uploadFileName;
				if(!GeneralHelper.isWindowsPlatform())
					path = path.replace('\\', File.separatorChar);
				
				return new File(path).getName();
			}
			
			return null;
		}
		
		void setUploadFileName(String uploadFileName)
		{
			this.uploadFileName = uploadFileName;
		}
		
		/** ��ȡ��������ϴ��ļ��� {@link File} ���� */
		public File getSaveFile()
		{
			return saveFile;
		}
		
		void setSaveFile(File saveFile)
		{
			this.saveFile = saveFile;
		}
	}
	
	private class FileItemInfo
	{
		FileItem item;
		File file;
		
		public FileItemInfo(FileItem item, File file)
		{
			this.item	= item;
			this.file	= file;
		}
	}
	
	/** �ļ��ϴ����ö��ֵ */
	public static enum Result
	{
		/** �ɹ� */
		SUCCESS,
		/** ʧ�ܣ��ļ��ܴ�С�������� */
		SIZE_EXCEEDED,
		/** ʧ�ܣ������ļ���С�������� */
		FILE_SIZE_EXCEEDED,
		/** ʧ�ܣ���������Ͳ���ȷ */
		INVALID_CONTENT_TYPE,
		/** ʧ�ܣ��ļ��ϴ� IO ���� */
		FILE_UPLOAD_IO_EXCEPTION,
		/** ʧ�ܣ������ϴ����������쳣 */
		OTHER_PARSE_REQUEST_EXCEPTION,
		/** ʧ�ܣ��ļ����Ͳ���ȷ */
		INVALID_FILE_TYPE,
		/** ʧ�ܣ��ļ�д��ʧ�� */
		WRITE_FILE_FAIL,
		/** ʧ�ܣ��ļ�����·������ȷ */
		INVALID_SAVE_PATH;	
	}

}
