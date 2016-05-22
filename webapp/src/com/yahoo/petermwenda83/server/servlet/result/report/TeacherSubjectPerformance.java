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

public class TeacherSubjectPerformance extends HttpServlet {




	private Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD);
	private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font normalText = new Font(Font.FontFamily.COURIER, 12);
	private Document document;
	private PdfWriter writer;
	private Cache schoolaccountCache, statisticsCache;

	private Logger logger;
	ExamConfig examConfig;
	GradingSystem gradingSystem;
	private String PDF_SUBTITLE ="";
	private String  firstnamee = "";

	private static PerfomanceDAO perfomanceDAO;
	private static SubjectDAO subjectDAO;
	private static ClassTeacherDAO classTeacherDAO;
	private static StudentDAO studentDAO;
	private static RoomDAO roomDAO;
	private static ExamConfigDAO examConfigDAO;
	private static GradingSystemDAO gradingSystemDAO;
	private static TermFeeDAO termFeeDAO;
	private static StudentAmountDAO studentAmountDAO;

	

	HashMap<String, String> studentAdmNoHash = new HashMap<String, String>();
	HashMap<String, String> firstnameHash = new HashMap<String, String>();
	HashMap<String, String> studNameHash = new HashMap<String, String>();
	HashMap<String, String> roomHash = new HashMap<String, String>();


	double score = 0;
	double engscore = 0;String engscorestr = "";
	double kswscore = 0;String kswscorestr = "";
	double matscore = 0;String matscorestr = "";
	double physcore = 0;String physcorestr = "";  
	double bioscore = 0;String bioscorestr = "";
	double chemscore = 0;String chemscorestr = "";
	double bsscore = 0;String bsscorestr = "";
	double comscore = 0;String comscorestr = "";
	double hscscore = 0;String hscscorestr = "";
	double agriscore = 0;String agriscorestr = "";
	double geoscore = 0;String geoscorestr = "";
	double crescore = 0;String crescorestr = "";
	double histscore = 0;String histscorestr = "";

	String grade = "";
	String studeadmno = "";
	String studename = "";
	String admno = "";

	double paper1  = 0;
	double paper2  = 0;
	double paper3  = 0;
	double total  = 0;
	double pmean  = 0;

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

	//MATH_UUID,PHY_UUID,BIO_UUID,CHEM_UUID,BS_UUID,COMP_UUID,H_S,AGR_UUID,GEO_UUID,CRE_UUID,HIST_UUID

	int position = 1;
	static int Finalposition = 0;

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
		classTeacherDAO = ClassTeacherDAO.getInstance();
		studentDAO = StudentDAO.getInstance();
		roomDAO = RoomDAO.getInstance();
		examConfigDAO = ExamConfigDAO.getInstance();
		gradingSystemDAO = GradingSystemDAO.getInstance();
		studentAmountDAO = StudentAmountDAO.getInstance();
		termFeeDAO = TermFeeDAO.getInstance();



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
		classroomuuid = StringUtils.trimToEmpty(request.getParameter("classroomUuid"));

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
		gradingSystem = gradingSystemDAO.getGradingSystem(school.getUuid());

		

		SessionStatistics statistics = new SessionStatistics();
		if ((element = statisticsCache.get(schoolusername)) != null) {
			statistics = (SessionStatistics) element.getObjectValue();
		}

		List<Perfomance> perfomanceList = new ArrayList<Perfomance>(); 
		perfomanceList = perfomanceDAO.getClassPerfomanceListGeneral(school.getUuid(), classroomuuid,examConfig.getTerm(),examConfig.getYear());
		List<Perfomance> pDistinctList = new ArrayList<Perfomance>();
		pDistinctList = perfomanceDAO.getPerfomanceListDistinctGeneral(school.getUuid(), classroomuuid,examConfig.getTerm(),examConfig.getYear());


		List<Student> studentList = new ArrayList<Student>(); 
		studentList = studentDAO.getAllStudents(school.getUuid(),classroomuuid);

		for(Student stu : studentList){
			studentAdmNoHash.put(stu.getUuid(),stu.getAdmno()); 
			String firstNameLowecase = stu.getFirstname().toLowerCase();
			String lastNameLowecase = stu.getLastname().toLowerCase();
			String surnameLowecase = stu.getSurname().toLowerCase();
			String formatedFirstname = firstNameLowecase.substring(0,1).toUpperCase()+firstNameLowecase.substring(1);
			String formatedLastname = lastNameLowecase.substring(0,1).toUpperCase()+lastNameLowecase.substring(1);
			String formatedsurname = surnameLowecase.substring(0,1).toUpperCase()+surnameLowecase.substring(1);
			studNameHash.put(stu.getUuid(),formatedFirstname + " " + formatedLastname + " " + formatedsurname +"\n"); 
			firstnameHash.put(stu.getUuid(), formatedFirstname);
		}

		List<ClassRoom> classroomList = new ArrayList<ClassRoom>(); 
		classroomList = roomDAO.getAllRooms(school.getUuid()); 
		for(ClassRoom c : classroomList){
			roomHash.put(c.getUuid() , c.getRoomName());
		}

		document = new Document(PageSize.A3, 46, 46, 64, 64);

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

			// addEmptyLine(preface, 1);
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
			MiddleNumberFor3 middle = new MiddleNumberFor3();
			if(pDistinctList !=null){
				int mycount =1;
				for(Perfomance s : pDistinctList){                              
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




					Finalposition = mycount++;

				}




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



				position = 1;
				int counttwo = 1;

				for(Object o : as){
					String items = String.valueOf(o);
					String [] item = items.split("=");
					String uuid = item[0];
					totalz = item[1];
					double mean = 0;
					mean = Double.parseDouble(totalz)/7;  



					studeadmno = studentAdmNoHash.get(uuid);
					studename = studNameHash.get(uuid);  
					firstnamee = firstnameHash.get(uuid);

					StudentAmount studentAmount = new StudentAmount();
					if(studentAmountDAO.getStudentAmount(school.getUuid(), uuid) !=null){
						studentAmount = studentAmountDAO.getStudentAmount(school.getUuid(), uuid);
					}

					TermFee termFee = termFeeDAO.getFee(school.getUuid(),examConfig.getTerm(),examConfig.getYear());




					if(engscorehash.get(uuid)!=null){
						engscore = engscorehash.get(uuid);  
						engscorestr =  rf2.format(engscore);

					}else{
						engscorestr = "";
					}


					if(kswscoreMap.get(uuid)!=null){
						kswscore = kswscoreMap.get(uuid);               
						kswscorestr = rf2.format(kswscore);

					}else{
						kswscorestr = "";
					}


					if(physcoreMap.get(uuid)!=null){
						physcore = physcoreMap.get(uuid);              
						physcorestr = rf2.format(physcore);

					}else{
						physcorestr = "";
					}

					if(bioscoreMap.get(uuid)!=null){
						bioscore = bioscoreMap.get(uuid);              
						bioscorestr = rf2.format(bioscore);

					}else{
						bioscorestr = "";
					}


					if(chemscorehash.get(uuid)!=null){
						chemscore = chemscorehash.get(uuid);              
						chemscorestr = rf2.format(chemscore);


					}else{
						chemscorestr = "";
					}



					if(matscorehash.get(uuid)!=null){
						matscore = matscorehash.get(uuid);              
						matscorestr = rf2.format(matscore);

					}else{
						matscorestr = "";
					}

					if(histscoreMap.get(uuid)!=null){
						histscore = histscoreMap.get(uuid);              
						histscorestr = rf2.format(histscore);

					}else{
						histscorestr = "";
					}


					if(crescorehash.get(uuid)!=null){
						crescore = crescorehash.get(uuid);              
						crescorestr = rf2.format(crescore);

					}else{
						crescorestr = "";
					}


					if(geoscoreMap.get(uuid)!=null){
						geoscore = geoscoreMap.get(uuid);               
						geoscorestr = rf2.format(geoscore);

					}else{
						geoscorestr = "";
					}


					if(bsscoreMap.get(uuid)!=null){
						bsscore = bsscoreMap.get(uuid);             
						bsscorestr = rf2.format(bsscore);

					}else{
						bsscorestr = "";
					}


					if(agriscorehash.get(uuid)!=null){
						agriscore = agriscorehash.get(uuid);              
						agriscorestr = rf2.format(agriscore);

					}else{
						agriscorestr = "";
					}

					if(hscscoreMap.get(uuid)!=null){
						hscscore = hscscoreMap.get(uuid);              
						hscscorestr = rf2.format(hscscore);

					}else{
						hscscorestr = "";
					}

					if(comscoreMap.get(uuid)!=null){
						comscore = comscoreMap.get(uuid);               
						comscorestr = rf2.format(comscore);

					}else{
						comscorestr = "";
					}   

					BaseColor baseColor = new BaseColor(32,178,170);//maroon
					BaseColor Colormagenta = new BaseColor(176,196,222);//magenta
					BaseColor Colorgrey = new BaseColor(128,128,128);//gray,grey

					Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

					Paragraph emptyline = new Paragraph(("                              "));

					//table here
					PdfPCell CountHeader = new PdfPCell(new Paragraph("*",boldFont));
					CountHeader.setBackgroundColor(baseColor);
					CountHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell subjectHeader = new PdfPCell(new Paragraph("SUBJECT",boldFont));
					subjectHeader.setBackgroundColor(baseColor);
					subjectHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell scoreHeader = new PdfPCell(new Paragraph("SCORE",boldFont));
					scoreHeader.setBackgroundColor(baseColor);
					scoreHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeHeader = new PdfPCell(new Paragraph("GRADE",boldFont));
					gradeHeader.setBackgroundColor(baseColor);
					gradeHeader.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell remarkHeader = new PdfPCell(new Paragraph("REMARKS",boldFont));
					remarkHeader.setBackgroundColor(baseColor);
					remarkHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
					PdfPTable myTable;

					myTable = new PdfPTable(5); 
					myTable.addCell(CountHeader);
					myTable.addCell(subjectHeader);
					myTable.addCell(scoreHeader);
					myTable.addCell(gradeHeader);
					myTable.addCell(remarkHeader);
					myTable.setWidthPercentage(100); 
					myTable.setWidths(new int[]{15,60,25,25,80});   
					myTable.setHorizontalAlignment(Element.ALIGN_LEFT);

					String maxscore = "100";


					String aplain = Integer.toString(gradingSystem.getGradeAplain());
					String aminus = Integer.toString(gradingSystem.getGradeAminus());

					String bplus = Integer.toString(gradingSystem.getGradeBplus());
					String bplain = Integer.toString(gradingSystem.getGradeBplain());
					String bminus = Integer.toString(gradingSystem.getGradeBminus());

					String cplus = Integer.toString(gradingSystem.getGradeCplus());
					String cplain = Integer.toString(gradingSystem.getGradeCplain());
					String cminus = Integer.toString(gradingSystem.getGradeCminus());

					String dplus = Integer.toString(gradingSystem.getGradeDplus());
					String dplain = Integer.toString(gradingSystem.getGradeDplain());
					String dminus = Integer.toString(gradingSystem.getGradeDminus());

					String gradeE = Integer.toString(gradingSystem.getGradeE());


					PdfPCell gradeA = new PdfPCell(new Paragraph(maxscore+"-"+aplain,boldFont));
					gradeA.setBackgroundColor(baseColor);
					gradeA.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeAminus = new PdfPCell(new Paragraph(aplain+"-"+aminus,boldFont));
					gradeAminus.setBackgroundColor(baseColor);
					gradeAminus.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeBplus = new PdfPCell(new Paragraph(aminus+"-"+bplus,boldFont));
					gradeBplus.setBackgroundColor(baseColor);
					gradeBplus.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeBplain = new PdfPCell(new Paragraph(bplus+"-"+bplain,boldFont));
					gradeBplain.setBackgroundColor(baseColor);
					gradeBplain.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeBminus = new PdfPCell(new Paragraph(bplain+"-"+bminus,boldFont));
					gradeBminus.setBackgroundColor(baseColor);
					gradeBminus.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeCplus = new PdfPCell(new Paragraph(bminus+"-"+cplus,boldFont));
					gradeCplus.setBackgroundColor(baseColor);
					gradeCplus.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeCplain = new PdfPCell(new Paragraph(cplus+"-"+cplain,boldFont));
					gradeCplain.setBackgroundColor(baseColor);
					gradeCplain.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeCminus = new PdfPCell(new Paragraph(cplain+"-"+cminus,boldFont));
					gradeCminus.setBackgroundColor(baseColor);
					gradeCminus.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeDplus = new PdfPCell(new Paragraph(cminus+"-"+dplus,boldFont));
					gradeDplus.setBackgroundColor(baseColor);
					gradeDplus.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeDplain = new PdfPCell(new Paragraph(dplus+"-"+dplain,boldFont));
					gradeDplain.setBackgroundColor(baseColor);
					gradeDplain.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeDminus = new PdfPCell(new Paragraph(dplain+"-"+dminus,boldFont));
					gradeDminus.setBackgroundColor(baseColor);
					gradeDminus.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell gradeEE = new PdfPCell(new Paragraph(dminus+"-"+gradeE,boldFont));
					gradeEE.setBackgroundColor(baseColor);
					gradeEE.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPTable gradeTable = new PdfPTable(12);  
					gradeTable.addCell(gradeA);
					gradeTable.addCell(gradeAminus);
					gradeTable.addCell(gradeBplus);
					gradeTable.addCell(gradeBplain);
					gradeTable.addCell(gradeBminus);
					gradeTable.addCell(gradeCplus);
					gradeTable.addCell(gradeCplain);
					gradeTable.addCell(gradeCminus);
					gradeTable.addCell(gradeDplus);
					gradeTable.addCell(gradeDplain);
					gradeTable.addCell(gradeDminus);
					gradeTable.addCell(gradeEE);
					gradeTable.setWidthPercentage(100); 
					gradeTable.setWidths(new int[]{20,20,20,20,20,25,20,20,20,20,20,20});   
					gradeTable.setHorizontalAlignment(Element.ALIGN_LEFT);

					gradeTable.addCell("A ");
					gradeTable.addCell("A-");
					gradeTable.addCell("B+");
					gradeTable.addCell("B");
					gradeTable.addCell("B-");
					gradeTable.addCell("C+");
					gradeTable.addCell("C");
					gradeTable.addCell("C-");
					gradeTable.addCell("D+");
					gradeTable.addCell("D");
					gradeTable.addCell("D-");
					gradeTable.addCell("E");

					PdfPTable containerTable = new PdfPTable(2);  
					containerTable.setWidthPercentage(100); 
					containerTable.setWidths(new int[]{100,100}); 
					containerTable.setHorizontalAlignment(Element.ALIGN_LEFT);


					PdfPTable titleTable = new PdfPTable(1);  
					titleTable.setWidthPercentage(25); 
					titleTable.setWidths(new int[]{100}); 
					titleTable.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPTable HeaderTable = new PdfPTable(1);  
					HeaderTable.setWidthPercentage(50); 
					HeaderTable.setWidths(new int[]{100});   
					HeaderTable.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPTable prefaceTable = new PdfPTable(2);  
					prefaceTable.setWidthPercentage(100); 
					prefaceTable.setWidths(new int[]{100,100}); 

					Paragraph content = new Paragraph();
					content.add(new Paragraph((PDF_SUBTITLE +"\n\n\n\n\n\n\n\n\n\n\n") , smallBold));

					Paragraph reporttitle = new Paragraph();
					reporttitle.add(new Paragraph(("STUDENT REPORT CARD") , smallBold));

					PdfPCell contentcell = new PdfPCell(content);
					contentcell.setBorder(Rectangle.NO_BORDER); 
					contentcell.setHorizontalAlignment(Element.ALIGN_RIGHT);

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

					prefaceTable.addCell(logo); 
					prefaceTable.addCell(contentcell);


					PdfPCell contheader = new PdfPCell(new Paragraph(("TERM " +examConfig.getTerm() + ": YEAR " + examConfig.getYear() +"\n\n" +("CLASS : " + roomHash.get(classroomuuid) +"\n")) +"",boldFont));
					contheader.setBackgroundColor(Colormagenta);
					contheader.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell bodyheader = new PdfPCell(new Paragraph(("ADMISSION NUMBER : " + studentAdmNoHash.get(uuid) +"\n\n" +("STUDENT NAME : " + studNameHash.get(uuid) +"\n")),boldFont));
					bodyheader.setBackgroundColor(Colormagenta);
					bodyheader.setHorizontalAlignment(Element.ALIGN_LEFT);


					containerTable.addCell(bodyheader);
					containerTable.addCell(contheader);



					List<Subject> subList = subjectDAO.getAllSubjects();

					int count = 1;
					for(Subject sub : subList){



						myTable.addCell(" "+count);

						if(StringUtils.equals(sub.getUuid(), ENG_UUID)){
							myTable.addCell(sub.getSubjectName());        		  
							myTable.addCell(" "+engscorestr);
							myTable.addCell(" "+computeGrade(engscore));
							myTable.addCell(" "+computeRemarks(engscore));
							engscore = 0;

						}if(StringUtils.equals(sub.getUuid(), KISWA_UUID)){
							myTable.addCell(sub.getSubjectName());        		  
							myTable.addCell(" "+kswscorestr);
							myTable.addCell(" "+computeGrade(kswscore));
							myTable.addCell(" "+computeRemarks(kswscore));	  
							kswscore = 0;

						}if(StringUtils.equals(sub.getUuid(), MATH_UUID)){
							myTable.addCell(sub.getSubjectName());        		  
							myTable.addCell(" "+matscorestr);  
							myTable.addCell(" "+computeGrade(matscore));
							myTable.addCell(" "+computeRemarks(matscore));
							matscore = 0;

						}if(StringUtils.equals(sub.getUuid(), PHY_UUID)){
							myTable.addCell(sub.getSubjectName());        		  
							myTable.addCell(" "+physcorestr); 
							myTable.addCell(" "+computeGrade(physcore));
							myTable.addCell(" "+computeRemarks(physcore));  
							physcore = 0;

						}if(StringUtils.equals(sub.getUuid(), BIO_UUID)){
							myTable.addCell(sub.getSubjectName());        		    
							myTable.addCell(" "+bioscorestr);      
							myTable.addCell(" "+computeGrade(bioscore));
							myTable.addCell(" "+computeRemarks(bioscore));
							bioscore = 0;

						}if(StringUtils.equals(sub.getUuid(), CHEM_UUID)){
							myTable.addCell(sub.getSubjectName());        		  
							myTable.addCell(" "+chemscorestr); 
							myTable.addCell(" "+computeGrade(chemscore));
							myTable.addCell(" "+computeRemarks(chemscore));
							chemscore = 0;

						}if(StringUtils.equals(sub.getUuid(), BS_UUID)){
							myTable.addCell(sub.getSubjectName());        		    
							myTable.addCell(" "+bsscorestr);     
							myTable.addCell(" "+computeGrade(bsscore));
							myTable.addCell(" "+computeRemarks(bsscore));
							bsscore = 0;

						}if(StringUtils.equals(sub.getUuid(), COMP_UUID)){
							myTable.addCell(sub.getSubjectName());        		  
							myTable.addCell(" "+comscorestr);  
							myTable.addCell(" "+computeGrade(comscore));
							myTable.addCell(" "+computeRemarks(comscore));
							comscore = 0;

						}if(StringUtils.equals(sub.getUuid(), H_S)){
							myTable.addCell(sub.getSubjectName());        		  
							myTable.addCell(" "+hscscorestr);
							myTable.addCell(" "+computeGrade(hscscore));
							myTable.addCell(" "+computeRemarks(hscscore));
							hscscore = 0;

						}if(StringUtils.equals(sub.getUuid(), AGR_UUID)){
							myTable.addCell(sub.getSubjectName());        		  
							myTable.addCell(" "+agriscorestr);      
							myTable.addCell(" "+computeGrade(agriscore));
							myTable.addCell(" "+computeRemarks(agriscore));
							agriscore = 0;

						}if(StringUtils.equals(sub.getUuid(), GEO_UUID)){
							myTable.addCell(sub.getSubjectName());        		  
							myTable.addCell(" "+geoscorestr);     
							myTable.addCell(" "+computeGrade(geoscore));
							myTable.addCell(" "+computeRemarks(geoscore));
							geoscore = 0;

						}if(StringUtils.equals(sub.getUuid(), CRE_UUID)){
							myTable.addCell(sub.getSubjectName());        		  
							myTable.addCell(" "+crescorestr);   
							myTable.addCell(" "+computeGrade(crescore));
							myTable.addCell(" "+computeRemarks(crescore));
							crescore = 0;

						}if(StringUtils.equals(sub.getUuid(), HIST_UUID)){
							myTable.addCell(sub.getSubjectName());  
							myTable.addCell(" "+histscorestr);    
							myTable.addCell(" "+computeGrade(histscore));
							myTable.addCell(" "+computeRemarks(histscore));
							histscore = 0;
						}


						count++;
					}



					Paragraph myposition;

					if(mean==number){
						myposition = new Paragraph(("POSITION " +(position-counttwo++)+ " OUT OF " +Finalposition));
					}
					else{
						counttwo=1;
						myposition = new Paragraph(("POSITION " +position+ " OUT OF " +Finalposition));
					}

					PdfPCell positionheader = new PdfPCell(new Paragraph(myposition +"\n\n",boldFont));
					positionheader.setBackgroundColor(Colormagenta);
					positionheader.setHorizontalAlignment(Element.ALIGN_LEFT);  

					PdfPCell meanheader = new PdfPCell(new Paragraph(("MEAN SCORE " + df.format(mean) + " GRADE " +computeGrade(mean)) +"\n\n",boldFont));
					meanheader.setBackgroundColor(Colormagenta);
					meanheader.setHorizontalAlignment(Element.ALIGN_RIGHT);

					PdfPTable bottomTable = new PdfPTable(2);  
					bottomTable.setWidthPercentage(100); 
					bottomTable.setWidths(new int[]{100,100}); 
					bottomTable.setHorizontalAlignment(Element.ALIGN_LEFT);

					bottomTable.addCell(positionheader);
					bottomTable.addCell(meanheader); 

					PdfPTable feeTable = new PdfPTable(2);  
					feeTable.setWidthPercentage(100); 
					feeTable.setWidths(new int[]{100,100}); 
					feeTable.setHorizontalAlignment(Element.ALIGN_LEFT);

					double amountpaid = 0.0;
					double balance = 0.0;

					//examConfig.getTerm()
					double termfee = termFee.getTermAmount();
					String currentTermstr = examConfig.getTerm();
					String correctTermstr = "";
					int correctTermint = 0;
					int currentTermint = Integer.parseInt(currentTermstr); 
					int nextTermint = currentTermint+1;
					if(nextTermint >3){
						correctTermint = 1;
						correctTermstr = Integer.toString(correctTermint);
					}else{
						correctTermint = currentTermint+1;
						correctTermstr = Integer.toString(correctTermint);
					}

					TermFee termFeenex = termFeeDAO.getFee(school.getUuid(),correctTermstr,examConfig.getYear()); 
					double nexttermfee = termFeenex.getTermAmount(); 

					amountpaid = studentAmount.getAmount();
					balance =  termfee - amountpaid;

					Locale locale = new Locale("en","KE"); 
					NumberFormat nf = NumberFormat.getCurrencyInstance(locale);


					PdfPCell feeCell = new PdfPCell(new Paragraph("Closing Fee Balance  " + nf.format(balance)+" \n\n Next Term Fee " + nf.format(nexttermfee) ,boldFont));
					feeCell.setBackgroundColor(Colorgrey);
					feeCell.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell DateCell = new PdfPCell(new Paragraph((("Clossing date : " +formatter.format(new Date()))+" \n\n Next Term Opening date :_________________ ")+"\n\n",boldFont));
					DateCell.setBackgroundColor(Colorgrey);
					DateCell.setHorizontalAlignment(Element.ALIGN_LEFT);

					feeTable.addCell(feeCell);
					feeTable.addCell(DateCell);



					PdfPTable commentTable = new PdfPTable(2);  
					commentTable.setWidthPercentage(100); 
					commentTable.setWidths(new int[]{100,100}); 
					commentTable.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell commentCell = new PdfPCell(new Paragraph("Thank you " + firstnamee + " for the fantastic term, it has been awesome to see you grow "
							+ "and develop, hope you have a wonderful holiday.\nFor your performance, all we can say is ... " + classteacherRemarks(mean)+"\n\n"));
					commentCell.setBackgroundColor(Colormagenta);
					commentCell.setColspan(2); 
					commentCell.setHorizontalAlignment(Element.ALIGN_LEFT);

					PdfPCell Cell1 = new PdfPCell(new Paragraph("Class Teacher's Signature: _____________________\n\n"
							+ "Principal's Signature: _____________________\n\n"));

					Cell1.setBackgroundColor(Colormagenta);
					Cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);

					PdfPCell Cell2 = new PdfPCell(new Paragraph("Date : _____________________\n\n"
							+ "Date : _____________________\n\n"));
					Cell2.setBackgroundColor(Colormagenta);
					Cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

					commentTable.addCell(commentCell);
					commentTable.addCell(Cell1);
					commentTable.addCell(Cell2);

					document.add(prefaceTable);
					//document.add(reporttitle);
					document.add(emptyline);

					document.add(containerTable);      	  
					document.add(emptyline);
					document.add(myTable); 
					document.add(emptyline);
					document.add(gradeTable);  
					document.add(emptyline);
					document.add(bottomTable); 
					document.add(emptyline);
					document.add(feeTable);
					document.add(emptyline);  //commentTable
					document.add(commentTable);


					document.newPage();


					position++;
					number=mean;


				}


			}

			// step 5
			document.close();
		}
		catch(DocumentException e) {
			logger.error("DocumentException while writing into the document");
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}

	private String classteacherRemarks(double score) {
		String remarks = "";
		double mean = score;

		if(mean >= gradingSystem.getGradeAplain()){
			remarks = "Execellent work,let sky be your steping stone.";
		}else if(mean >= gradingSystem.getGradeAminus()){
			remarks = "Good work, sky is not the limit.";
		}else if(mean >= gradingSystem.getGradeBplus()){
			remarks = "Good work, a little more effort please.";
		}else if(mean >= gradingSystem.getGradeBplain()){
			remarks = "Good work but, this is not your best.";
		}else if(mean >= gradingSystem.getGradeBminus()){
			remarks = "An average student, you have the potential.";
		}else if(mean >= gradingSystem.getGradeCplus()){
			remarks = "Below average, this is not your best.";
		}else if(mean >= gradingSystem.getGradeCplain()){
			remarks = "Below average, you can do beter than this.";
		}else if(mean >= gradingSystem.getGradeCminus()){
			remarks = "Below average, you can do beter.";
		}else if(mean >= gradingSystem.getGradeDplus()){
			remarks = "Below average, put more effort.";
		}else if(mean >= gradingSystem.getGradeDplain()){
			remarks = "Far below average, please you deserve better.";
		}else if(mean >= gradingSystem.getGradeDminus()){
			remarks = "Far much below average but you deserve beter than this.";
		}else{
			remarks = "This is extremly poor but still you can do beter.";
		}

		if(mean ==0){
			remarks = " ";
		}

		return remarks;
	}

	private String computeRemarks(double score) {
		String remarks = "";
		double mean = score;

		if(mean >= gradingSystem.getGradeAplain()){
			remarks = "Execellent, keep it up.";
		}else if(mean >= gradingSystem.getGradeAminus()){
			remarks = "Good work, aim higher.";
		}else if(mean >= gradingSystem.getGradeBplus()){
			remarks = "Good job, aim high.";
		}else if(mean >= gradingSystem.getGradeBplain()){
			remarks = "well done, aim higher.";
		}else if(mean >= gradingSystem.getGradeBminus()){
			remarks = "well done, aim high.";
		}else if(mean >= gradingSystem.getGradeCplus()){
			remarks = "Fair, you can do better.";
		}else if(mean >= gradingSystem.getGradeCplain()){
			remarks = "Fair, put more effort.";
		}else if(mean >= gradingSystem.getGradeCminus()){
			remarks = "Fair, you can improve.";
		}else if(mean >= gradingSystem.getGradeDplus()){
			remarks = "Fair, pull up your socks.";
		}else if(mean >= gradingSystem.getGradeDplain()){
			remarks = "You can do better than this.";
		}else if(mean >= gradingSystem.getGradeDminus()){
			remarks = "You can do better than this.";
		}else{
			remarks = "You can do better than this.";
		}

		if(mean ==0){
			remarks = " ";
		}

		return remarks;
	}

	/**
	 * @param engscore2
	 * @return
	 */
	private String computeGrade(double score) {
		double mean = score;
		if(mean >= gradingSystem.getGradeAplain()){
			grade = "A";
		}else if(mean >= gradingSystem.getGradeAminus()){
			grade = "A-";
		}else if(mean >= gradingSystem.getGradeBplus()){
			grade = "B+";
		}else if(mean >= gradingSystem.getGradeBplain()){
			grade = "B";
		}else if(mean >= gradingSystem.getGradeBminus()){
			grade = "B-";
		}else if(mean >= gradingSystem.getGradeCplus()){
			grade = "C+";
		}else if(mean >= gradingSystem.getGradeCplain()){
			grade = "C";
		}else if(mean >= gradingSystem.getGradeCminus()){
			grade = "C-";
		}else if(mean >= gradingSystem.getGradeDplus()){
			grade = "D+";
		}else if(mean >= gradingSystem.getGradeDplain()){
			grade = "D";
		}else if(mean >= gradingSystem.getGradeDminus()){
			grade = "D-";
		}else{
			grade = "E";
		}

		if(mean ==0){
			grade = " ";
		}

		return grade;
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
