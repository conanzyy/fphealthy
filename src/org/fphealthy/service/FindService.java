package org.fphealthy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.fphealthy.dao.Mydao;
import org.fphealthy.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindService {
	Logger log = Logger.getLogger("lavasoft"); 
	private Mydao dao;
	@Autowired
	public void setRuleDAO(Mydao dao) {
		this.dao = dao;
	}
	public HashMap<String,Object> getRelativeListForStr(String Astr,int index){
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("index", String.valueOf(index));
		map.put("str", Astr);
//		log.info( Astr+"--"+getRlist(Astr));
		map.put("relist", getRlist(Astr));
		return map;
	}
	@SuppressWarnings("unchecked")
	public List<String> getRlist(String str){
		String sql="select * from relations where astr like ? or bstr like ? ORDER BY  `orderi` ";
		Object[] objs=new Object[]{"%"+str+"%","%"+str+"%"};
		List<Map<String,Object>> templist= dao.queryForList(sql, objs);
		List<String> slits=new ArrayList<String>();
		slits.add(str);
		for(Map<String,Object> tempmap:templist){
			if(str.equals(tempmap.get("astr"))){
				if(!slits.contains(tempmap.get("bstr"))){
					slits.add((String)tempmap.get("bstr"));
					
				}
			}else{
				if(!slits.contains(tempmap.get("astr"))){
					slits.add((String)tempmap.get("astr"));
				}
			};
		}
//		HashSet<Object> h = new HashSet<Object>(slits);
//		List list=new ArrayList(h);
		return slits;
	}
	@SuppressWarnings("unchecked")
	public HashMap isSame(HashMap Amap,HashMap Bmap){
		HashMap tmap=new HashMap();
		tmap.put("flag", "false");
		List alist=(List) Amap.get("relist");
		List blist=(List) Bmap.get("relist");
		a:for(int i=0;i<alist.size();i++){
			 for(int j=0;j<blist.size();j++){
				if(alist.get(i).equals(blist.get(j))){
					tmap.put("aid", String.valueOf(i));
					tmap.put("bid", String.valueOf(j));
					tmap.put("flag", "true");
					break a;
				}
			}
		}
		tmap.put("aindex",Amap.get("index"));
		tmap.put("bindex",Bmap.get("index"));
		return tmap;
	}
	@SuppressWarnings("unchecked")
	public List<HashMap> isSameList(HashMap Amap,HashMap Bmap){
		List maplist=new ArrayList();
		List alist=(List) Amap.get("relist");
		List blist=(List) Bmap.get("relist");
		for(int i=0;i<alist.size();i++){
			 for(int j=0;j<blist.size();j++){
				if(alist.get(i).equals(blist.get(j))){
					HashMap tmap=new HashMap();
					tmap.put("aid", String.valueOf(i));
					tmap.put("bid", String.valueOf(j));
					tmap.put("flag", "true");
					tmap.put("aindex",Amap.get("index"));
					tmap.put("bindex",Bmap.get("index"));
					maplist.add(tmap);
				}
			}
		}
		return maplist;
	}
	@SuppressWarnings("unchecked")
	public boolean isSameBoo(HashMap Amap,HashMap Bmap){
		List alist=(List) Amap.get("relist");
		List blist=(List) Bmap.get("relist");
		for(int i=0;i<alist.size();i++){
			 for(int j=0;j<blist.size();j++){
				if(alist.get(i).equals(blist.get(j))){
					return true;
				}
			}
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<List<String>> showList(int n,String A,String B){
		if(n!=0){
		HashMap Amap=getRelativeListForStr(A,Factory.MAXINT-n);
		HashMap Bmap=getRelativeListForStr(B,Factory.MAXINT-n);
		//boolean bflag=isSameBoo(Amap,Bmap);
		List<HashMap> new_list=isSameList(Amap,Bmap);
		if(new_list.size()>0){
			List<List<String>> all_list=new ArrayList<List<String>>();
			for(HashMap map:new_list){
				List<String> temlist=new ArrayList<String>();
				temlist.add(A);
				List alist=(List) Amap.get("relist");
				//List blist=(List) Bmap.get("relist");
				String temstr=(String)alist.get(Integer.parseInt((String)map.get("aid")));
				if(!temlist.contains(temstr)){
					temlist.add(temstr);
				}
//				if(!temlist.contains(blist.get(Integer.parseInt((String)map.get("bid"))))){
//					temlist.add((String)blist.get(Integer.parseInt((String)map.get("bid"))));
//				}
				if(!temlist.contains(B)){
				temlist.add(B);
				}
				if(!all_list.contains(temlist)){
				all_list.add(temlist);
				}
			}
			return all_list;
		}else{
			List<String> alist=(List<String>) Amap.get("relist");
			List<String> blist=(List<String>) Bmap.get("relist");
//			Amap=getRelativeListForStr(A,Factory.MAXINT-n);
//			Bmap=getRelativeListForStr(B,Factory.MAXINT-n);
			List<List<String>> all_node_list=new ArrayList<List<String>>();
			for(int ti=0;ti<alist.size();ti++){
				for(int tj=0;tj<blist.size();tj++){
					String nodeA=alist.get(ti);
					String nodeB=blist.get(tj);
					List<HashMap> node_list=isSameList(getRelativeListForStr(nodeA,Factory.MAXINT-n),getRelativeListForStr(nodeB,Factory.MAXINT-n));
					if(node_list.size()>0){
//						List<List<String>> all_node_list=new ArrayList<List<String>>();
						for(HashMap map:node_list){
							List<String> temlist2=new ArrayList<String>();
							temlist2.add(A);
							if(!temlist2.contains(nodeA)){
								temlist2.add(nodeA);
							}
							List alist_node=(List) getRelativeListForStr(nodeA,Factory.MAXINT-n).get("relist");
							//List blist=(List) Bmap.get("relist");
							String temstr=(String)alist_node.get(Integer.parseInt((String)map.get("aid")));
							if(!temlist2.contains(temstr)){
								temlist2.add(temstr);
							}
							if(!temlist2.contains(nodeB)){
								temlist2.add(nodeB);
							}
//							if(!temlist.contains(blist.get(Integer.parseInt((String)map.get("bid"))))){
//								temlist.add((String)blist.get(Integer.parseInt((String)map.get("bid"))));
//							}
							if(!temlist2.contains(B)){
								temlist2.add(B);
							}
							if(!all_node_list.contains(temlist2)){
								all_node_list.add(temlist2);
							}
						}
					}
				}
			}
			return all_node_list;
			
		}
//		if(bflag){
//			slist.add(A);
//			HashMap map=isSame(Amap,Bmap);
//			List alist=(List) Amap.get("relist");
//		//	List blist=(List) Bmap.get("relist");
//		//	alist.get(Integer.parseInt((String)map.get("aid")));
//			if(!slist.contains(alist.get(Integer.parseInt((String)map.get("aid"))))){
//				slist.add((String)alist.get(Integer.parseInt((String)map.get("aid"))));
//			}
////			slist.add((String)blist.get(Integer.parseInt((String)map.get("bid"))));
//			slist.add(B);
//			System.out.println("==="+slist);
//			System.out.println("---"+map);
//			
//			System.out.println("get it!");
//		}else{
//			
//			
//		}
//		while(!bflag){
//			n--;
//			System.out.println("begin");
//			List<String> alist=(List<String>) Amap.get("relist");
//			List<String> blist=(List<String>) Bmap.get("relist");
////			Amap=getRelativeListForStr(A,i);
////			Bmap=getRelativeListForStr(B,i);
//			a:for(int ti=0;ti<alist.size();ti++){
//				for(int tj=0;tj<blist.size();tj++){
//					A=alist.get(ti);
//					B=blist.get(tj);
//					bflag=isSameBoo(getRelativeListForStr(A,Factory.MAXINT-n),getRelativeListForStr(B,Factory.MAXINT-n));
//					if(bflag){
//						break a;
//					}
//					//System.out.println(n);
//					//showList(n-1,A,B,slist);
//					//bflag=isSameBoo(getRelativeListForStr(alist.get(ti),i),getRelativeListForStr(blist.get(tj),i));
//				}
//			}
//		}
//		if(bflag){
//			HashMap map=isSame(Amap,Bmap);
//			System.out.println("----"+map);
//			
//			System.out.println("I get it!");
//		}
		}
		return null;
	}
}
