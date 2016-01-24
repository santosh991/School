/**
 * 
 */
package com.yahoo.petermwenda83.persistence.exam;

import java.util.List;

import com.yahoo.petermwenda83.bean.exam.CatOne;
import com.yahoo.petermwenda83.bean.exam.CatTwo;
import com.yahoo.petermwenda83.bean.exam.Common;
import com.yahoo.petermwenda83.bean.exam.EndTerm;
import com.yahoo.petermwenda83.bean.exam.PaperOne;
import com.yahoo.petermwenda83.bean.exam.PaperThree;
import com.yahoo.petermwenda83.bean.exam.PaperTwo;
import com.yahoo.petermwenda83.bean.exam.Perfomance;

/**
 * @author peter
 *
 */
public interface SchoolExamEngineDAO {
	
    public Common getCommon(String schoolAccountUuid,String classRoomUuid,String studentUuid,String subjectUuid);
	
	public boolean Checker(String schoolAccountUuid,String classRoomUuid,String studentUuid,String subjectUuid);
	
	public boolean putScore(Perfomance perfomance);
	
	public List<CatOne> getcatoneList(String schoolAccountUuid,String classRoomUuid);
	
	public List<CatTwo> getcatwoList(String schoolAccountUuid,String classRoomUuid);
	
	public List<EndTerm> getendtermList(String schoolAccountUuid,String classRoomUuid);
	
	public List<PaperOne> getPaperOneList(String schoolAccountUuid,String classRoomUuid);
	
	public List<PaperTwo> getPaperTwoList(String schoolAccountUuid,String classRoomUuid);
	
	public List<PaperThree> getpaperThreeList(String schoolAccountUuid,String classRoomUuid);

}

