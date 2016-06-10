package com.yahoo.petermwenda83.server.servlet.result.report;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.yahoo.petermwenda83.bean.classroom.ClassRoom;
import com.yahoo.petermwenda83.bean.exam.ExamConfig;
import com.yahoo.petermwenda83.bean.exam.GradingSystem;
import com.yahoo.petermwenda83.bean.exam.Perfomance;
import com.yahoo.petermwenda83.bean.money.StudentAmount;
import com.yahoo.petermwenda83.bean.money.TermFee;
import com.yahoo.petermwenda83.bean.schoolaccount.SchoolAccount;
import com.yahoo.petermwenda83.bean.student.Student;
import com.yahoo.petermwenda83.bean.subject.Subject;
import com.yahoo.petermwenda83.persistence.classroom.RoomDAO;
import com.yahoo.petermwenda83.persistence.exam.ExamConfigDAO;
import com.yahoo.petermwenda83.persistence.exam.GradingSystemDAO;
import com.yahoo.petermwenda83.persistence.exam.PerfomanceDAO;
import com.yahoo.petermwenda83.persistence.money.StudentAmountDAO;
import com.yahoo.petermwenda83.persistence.money.TermFeeDAO;
import com.yahoo.petermwenda83.persistence.staff.ClassTeacherDAO;
import com.yahoo.petermwenda83.persistence.student.StudentDAO;
import com.yahoo.petermwenda83.persistence.subject.SubjectDAO;
import com.yahoo.petermwenda83.server.cache.CacheVariables;
import com.yahoo.petermwenda83.server.servlet.result.PdfUtil;
import com.yahoo.petermwenda83.server.session.SessionConstants;
import com.yahoo.petermwenda83.server.session.SessionStatistics;
import com.yahoo.petermwenda83.server.util.magic.MiddleNumberFor3;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class CSPerformanceF34p extends HttpServlet{




	private Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
	private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font normalText = new Font(Font.FontFamily.COURIER, 12);
	private Document document;
	private PdfWriter writer;
	private Cache schoolaccountCache, statisticsCache;

	private Logger logger;
	ExamConfig examConfig;
	
	private String PDF_SUBTITLE ="";
	
	private static PerfomanceDAO perfomanceDAO;
	private static SubjectDAO subjectDAO;
	private static RoomDAO roomDAO;
	private static ExamConfigDAO examConfigDAO;
	
	double engscore = 0;
	double kswscore = 0;
	double matscore = 0;
	double physcore = 0;
	double bioscore = 0;
	double chemscore = 0;
	double bsscore = 0;
	double comscore = 0;
	double hscscore = 0;
	double agriscore = 0;
	double geoscore = 0;
	double crescore = 0;
	double histscore = 0;

	double paper1  = 0;
	double paper2  = 0;
	double paper3  = 0;

	//languages
	final String ENG_UUID = "D0F7EC32-EA25-7D32-8708-2CC132446";
	final String KISWA_UUID = "66027e51-b1ad-4b10-8250-63af64d23323";
	//sciences
	final String MATH_UUID = "4f59580d-1a16-4669-9ed5-4b89615d6903";
	final String PHY_UUID = "44f23b3c-e066-4b45-931c-0e8073d3a93a";
	final String BIO_UUID = "de0c86be-9bcb-4d3b-8098-b06687536c1f";
	final String CHEM_UUID = "552c0a24-6038-440f-add5-2dadfb9a23bd";
	//techinicals
	final String BS_UUID = "e1729cc2-524a-4069-b4a4-be5aec8473fe";
	final String COMP_UUID = "F1972BF2-C788-4F41-94FE-FBA1869C92BC";
	final String H_S = "C1F28FF4-1A18-4552-822A-7A4767643643";
	final String AGR_UUID = "b9bbd718-b32f-4466-ab34-42f544ff900e";
	//humanities 
	final String GEO_UUID = "0e5dc1c6-f62f-4a36-a1ec-064173332694";
	final String CRE_UUID = "f098e943-26fd-4dc0-b6a0-2d02477004a4";
	final String HIST_UUID = "c9caf109-c27d-4062-9b9f-ac4268629e27";

	String USER= "";
	String path ="";


	/**
	 *
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		logger = Logger.getLogger(this.getClass());
		CacheManager mgr = CacheManager.getInstance();
		schoolaccountCache = mgr.getCache(CacheVariables.CACHE_SCHOOL_ACCOUNTS_BY_USERNAME);
		statisticsCache = mgr.getCache(CacheVariables.CACHE_STATISTICS_BY_SCHOOL_ACCOUNT);
		perfomanceDAO = PerfomanceDAO.getInstance();
		subjectDAO = SubjectDAO.getInstance();
		roomDAO = RoomDAO.getInstance();
		examConfigDAO = ExamConfigDAO.getInstance();
		
		USER = System.getProperty("user.name");
		path = "/home/"+USER+"/school/logo/logo.png";
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @throws ServletException, IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ServletContext context = getServletContext();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename= \" results.pdf \" " );

		String classroomuuid = "";
		String schoolusername = "";
		classroomuuid = StringUtils.trimToEmpty(request.getParameter("classroomuuid"));

		SchoolAccount school = new SchoolAccount();
		HttpSession session = request.getSession(false);



		if(session !=null){
			schoolusername = (String) session.getAttribute(SessionConstants.SCHOOL_ACCOUNT_SIGN_IN_KEY);
			
		}
		net.sf.ehcache.Element element;

		element = schoolaccountCache.get(schoolusername);
		if(element !=null){
			school = (SchoolAccount) element.getObjectValue();
		}

		PDF_SUBTITLE = school.getSchoolName()+"\n"
				+ "P.O BOX "+school.getPostalAddress()+"\n" 
				+ ""+school.getTown().toUpperCase()+"-KENYA\n" 
				+ "" + school.getMobile()+"\n"
				+ "" + school.getEmail()+"\n"; 


		examConfig = examConfigDAO.getExamConfig(school.getUuid());
		

		SessionStatistics statistics = new SessionStatistics();
		if ((element = statisticsCache.get(schoolusername)) != null) {
			statistics = (SessionStatistics) element.getObjectValue();
		}
		
         //subject per class stream 
		List<Perfomance> perfomanceList = new ArrayList<Perfomance>(); 
		perfomanceList = perfomanceDAO.getPerfomanceList(school.getUuid(), classroomuuid,examConfig.getTerm(),examConfig.getYear());
		//distinct per class stream , distinct student
		List<Perfomance> pDistinctList = new ArrayList<Perfomance>();
		pDistinctList = perfomanceDAO.getPerfomanceListDistinct(school.getUuid(), classroomuuid,examConfig.getTerm(),examConfig.getYear());


		document = new Document(PageSize.A4, 46, 46, 64, 64);

		try {
			writer = PdfWriter.getInstance(document, response.getOutputStream());


			PdfUtil event = new PdfUtil();
			writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));
			writer.setPageEvent(event);

			populatePDFDocument(statistics, school,classroomuuid,perfomanceList,pDistinctList, path);


		} catch (DocumentException e) {
			logger.error("DocumentException while writing into the document");
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}



	/**
	 * @param statistics
	 * @param school
	 * @param classroomuuid
	 * @param perfomanceList
	 * @param pDistinctList
	 * @param realPath
	 */
	private void populatePDFDocument(SessionStatistics statistics, SchoolAccount school, String classroomuuid, 
			List<Perfomance> perfomanceList, List<Perfomance> pDistinctList,String realPath) {


		Map<String,Double> kswscoreMap = new LinkedHashMap<String,Double>();
		Map<String,Double> engscorehash = new LinkedHashMap<String,Double>(); 

		Map<String,Double> physcoreMap = new LinkedHashMap<String,Double>();
		Map<String,Double> matscorehash = new LinkedHashMap<String,Double>(); 
		Map<String,Double> bioscoreMap = new LinkedHashMap<String,Double>();
		Map<String,Double> chemscorehash = new LinkedHashMap<String,Double>(); 

		Map<String,Double> bsscoreMap = new LinkedHashMap<String,Double>();
		Map<String,Double> agriscorehash = new LinkedHashMap<String,Double>(); 
		Map<String,Double> hscscoreMap = new LinkedHashMap<String,Double>();
		Map<String,Double> comscoreMap = new LinkedHashMap<String,Double>();

		Map<String,Double> geoscoreMap = new LinkedHashMap<String,Double>();
		Map<String,Double> crescorehash = new LinkedHashMap<String,Double>(); 
		Map<String,Double> histscoreMap = new LinkedHashMap<String,Double>();

		String totalz = "";
		try {
			document.open();

			SimpleDateFormat formatter;
			formatter = new SimpleDateFormat("dd, MMM yyyy");


			DecimalFormat df = new DecimalFormat("0.00"); 
			df.setRoundingMode(RoundingMode.DOWN);

			DecimalFormat rf = new DecimalFormat("0.0"); 
			rf.setRoundingMode(RoundingMode.HALF_UP);

			DecimalFormat rf2 = new DecimalFormat("0"); 
			rf2.setRoundingMode(RoundingMode.UP);


			List<Perfomance> list = new ArrayList<>();
			Map<String,Double> grandscoremap = new LinkedHashMap<String,Double>(); 
			
			double languageScore = 0;double scienceScore = 0;double humanityScore = 0;
			double techinicalScore = 0;double grandscore = 0;double number = 0.0;
			
			double total = 0 ;
			
			
			MiddleNumberFor3 middle = new MiddleNumberFor3();
			if(pDistinctList !=null){
				
				for(Perfomance s : pDistinctList){ 
					//list per distict student
					list = perfomanceDAO.getPerformance(school.getUuid(), classroomuuid, s.getStudentUuid(),examConfig.getTerm(),examConfig.getYear());

					engscore = 0;kswscore = 0;matscore = 0;physcore = 0;bioscore = 0;chemscore = 0;
					bsscore = 0;comscore = 0;hscscore = 0;agriscore = 0;geoscore = 0;crescore = 0;
					histscore = 0;

					for(Perfomance pp : list){
						//Languages
						//Include all the languages
						if(true){
							if(StringUtils.equals(pp.getSubjectUuid(), ENG_UUID) ){

								paper1 = pp.getPaperOne(); //out of 60
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 60                   
								total = (paper1 + paper2 + paper3)/2; 
								engscore = total; 
								engscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(engscore)))));
								engscorehash.put(s.getStudentUuid(),engscore);


							}


							if(StringUtils.equals(pp.getSubjectUuid(), KISWA_UUID)){

								paper1 = pp.getPaperOne(); //out of 60
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 60
								total = (paper1 + paper2 + paper3)/2; 
								kswscore = total;
								kswscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(kswscore)))));
								kswscoreMap.put(s.getStudentUuid(),kswscore);

							}


							languageScore = (engscore+kswscore); 


						}       
						//Sciences
						//Pick best two if the student take the three
						if(true){
							double subjectBig = 0;
							double subjectSmall = 0;

							if(StringUtils.equals(pp.getSubjectUuid(), PHY_UUID)){

								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = ((paper1 + paper2)/160)*60 + paper3;

								physcore = total;
								physcore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(physcore)))));
								physcoreMap.put(s.getStudentUuid(),physcore);

							}
							if(StringUtils.equals(pp.getSubjectUuid(), BIO_UUID)){

								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = ((paper1 + paper2)/160)*60 + paper3;
								bioscore = total;
								bioscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(bioscore)))));
								bioscoreMap.put(s.getStudentUuid(),bioscore);

							}
							if(StringUtils.equals(pp.getSubjectUuid(), CHEM_UUID)){

								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = ((paper1 + paper2)/160)*60 + paper3;
								chemscore = total;
								chemscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(chemscore)))));
								chemscorehash.put(s.getStudentUuid(),chemscore);

							}
							if(StringUtils.equals(pp.getSubjectUuid(), MATH_UUID)){

								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								matscore = total;
								matscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(matscore)))));
								matscorehash.put(s.getStudentUuid(),matscore);

							}

							subjectBig = Math.max( (Math.max(physcore, bioscore)), Math.max(Math.max(physcore, bioscore), chemscore));
							subjectSmall = middle.ComputeMiddle(physcore, bioscore, chemscore);
							scienceScore = (subjectBig+subjectSmall+matscore);

						}
						//Technical  
						//Here we pick one subject, the one he/she has performed best, but this subject can be replaced by a science,
						//if the student takes 3 sciences and he/she performed better in the science than in all  the techinicals . 
						if(true){
							double bestTechinical = 0;
							if(StringUtils.equals(pp.getSubjectUuid(), BS_UUID)){

								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								bsscore = total;
								bsscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(bsscore)))));
								bsscoreMap.put(s.getStudentUuid(),bsscore);


							}
							if(StringUtils.equals(pp.getSubjectUuid(), AGR_UUID)){

								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = (paper1 + paper2)/2 + paper3;
								agriscore = total;
								agriscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(agriscore)))));
								agriscorehash.put(s.getStudentUuid(),agriscore);

							}     
							if(StringUtils.equals(pp.getSubjectUuid(), H_S)){

								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = (paper1 + paper2)/2 + paper3;
								hscscore = total;
								hscscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(hscscore)))));
								hscscoreMap.put(s.getStudentUuid(),hscscore);


							}if(StringUtils.equals(pp.getSubjectUuid(), COMP_UUID)){

								paper1 = pp.getPaperOne(); //out of 80
								paper2 = pp.getPaperTwo(); //out of 80
								paper3 = pp.getPaperThree();//out of 40
								total = (paper1 + paper2)/2 + paper3;
								comscore = total;
								comscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(comscore)))));
								comscoreMap.put(s.getStudentUuid(),comscore);

							} 
							bestTechinical = Math.max( (Math.max(bsscore, agriscore)), Math.max(hscscore, comscore));
							techinicalScore = bestTechinical; 

						}     

						//Humanities
						//Here we pick only one subject, the one the student has performed best . 
						if(true){  
							double bestHumanity = 0;     
							if(StringUtils.equals(pp.getSubjectUuid(), GEO_UUID)){

								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								geoscore = total;  
								geoscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(geoscore)))));
								geoscoreMap.put(s.getStudentUuid(),geoscore);

							}
							if(StringUtils.equals(pp.getSubjectUuid(), CRE_UUID)){

								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								crescore = total; 
								crescore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(crescore)))));
								crescorehash.put(s.getStudentUuid(),crescore);

							}
							if(StringUtils.equals(pp.getSubjectUuid(), HIST_UUID)){                	

								paper1 = pp.getPaperOne(); //out of 100
								paper2 = pp.getPaperTwo(); //out of 100
								total = (paper1 + paper2)/2;
								histscore = total;
								histscore = Double.parseDouble(rf2.format((double)Math.round(Double.parseDouble(rf.format(histscore)))));
								histscoreMap.put(s.getStudentUuid(),histscore);


							}


							bestHumanity = Math.max( (Math.max(geoscore, crescore)), Math.max(Math.max(geoscore, crescore), histscore));
							humanityScore = bestHumanity; 

						} 


					}

					grandscore = languageScore+scienceScore+humanityScore+techinicalScore;
					languageScore = 0; scienceScore = 0; humanityScore = 0;techinicalScore = 0;  
					grandscoremap.put(s.getStudentUuid(), grandscore);                        
					grandscore = 0;


				}//end distinct loop




				@SuppressWarnings("unchecked")
				ArrayList<?> as = new ArrayList(grandscoremap.entrySet());
				Collections.sort(as,new Comparator(){
					public int compare(Object o1,Object o2){
						Map.Entry e1 = (Map.Entry)o1;
						Map.Entry e2 = (Map.Entry)o2;
						Double f = (Double)e1.getValue();
						Double s = (Double)e2.getValue();
						return s.compareTo(f);
					}
				});



				int position = 1;
				int counttwo = 1;

				for(Object o : as){
					String items = String.valueOf(o);
					String [] item = items.split("=");
					String uuid = item[0];
					totalz = item[1];
					double mean = 0;
					mean = Double.parseDouble(totalz)/7;  





					if(engscorehash.get(uuid)!=null){
						engscore = engscorehash.get(uuid);  
						
					}
					if(kswscoreMap.get(uuid)!=null){
						kswscore = kswscoreMap.get(uuid);               

					}
					
					if(physcoreMap.get(uuid)!=null){
						physcore = physcoreMap.get(uuid);              

					}
					if(bioscoreMap.get(uuid)!=null){
						bioscore = bioscoreMap.get(uuid);              

					}

					if(chemscorehash.get(uuid)!=null){
						chemscore = chemscorehash.get(uuid);              
						
					}
					if(matscorehash.get(uuid)!=null){
						matscore = matscorehash.get(uuid);              

					}
					if(histscoreMap.get(uuid)!=null){
						histscore = histscoreMap.get(uuid);              
			
					}

					if(crescorehash.get(uuid)!=null){
						crescore = crescorehash.get(uuid);              

					}
					if(geoscoreMap.get(uuid)!=null){
						geoscore = geoscoreMap.get(uuid);               

					}
					if(bsscoreMap.get(uuid)!=null){
						bsscore = bsscoreMap.get(uuid);             

					}
					if(agriscorehash.get(uuid)!=null){
						agriscore = agriscorehash.get(uuid);              

					}
					if(hscscoreMap.get(uuid)!=null){
						hscscore = hscscoreMap.get(uuid);
						
					}
					if(comscoreMap.get(uuid)!=null){
						comscore = comscoreMap.get(uuid);               

					}   

					BaseColor baseColor = new BaseColor(32,178,170);//maroon
					BaseColor Colormagenta = new BaseColor(176,196,222);//magenta
					BaseColor Colorgrey = new BaseColor(128,128,128);//gray,grey

					Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

					Paragraph emptyline = new Paragraph(("                              "));

					
					PdfPTable myTable;

					myTable = new PdfPTable(1); 
					
					myTable.setWidthPercentage(100); 
					myTable.setWidths(new int[]{100});   
					myTable.setHorizontalAlignment(Element.ALIGN_LEFT);

					

					Paragraph preface = new Paragraph();
					preface.add(createImage(realPath));

					Image imgLogo = null;
					try {
						imgLogo = Image.getInstance(realPath);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					imgLogo.scalePercent(14); 
					imgLogo.setAlignment(Element.ALIGN_LEFT);

					PdfPCell logo = new PdfPCell();
					logo.addElement(new Chunk(imgLogo,30,-140)); // margin left  ,  margin top
					logo.setBorder(Rectangle.NO_BORDER); 
					logo.setHorizontalAlignment(Element.ALIGN_LEFT);

					

					


				}//end for lood sorted


			}

			// step 5
			document.close();
		}
		catch(DocumentException e) {
			logger.error("DocumentException while writing into the document");
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}

	
	

	/**
	 * @param realPath
	 * @return
	 */
	private Element createImage(String realPath) {
		Image imgLogo = null;

		try {
			imgLogo = Image.getInstance(realPath);
			imgLogo.scaleToFit(200, 200);
			imgLogo.setAlignment(Element.ALIGN_CENTER);

		} catch (BadElementException e) {
			logger.error("BadElementException Exception while creating an image");
			logger.error(ExceptionUtils.getStackTrace(e));

		} catch (MalformedURLException e) {
			logger.error("MalformedURLException for the path");
			logger.error(ExceptionUtils.getStackTrace(e));

		} catch (IOException e) {
			logger.error("IOException while creating an image");
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return imgLogo;
	}



	/**
	 *
	 * @param request
	 * @param response
	 * @throws ServletException, IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3513371438433721109L;


}





/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
