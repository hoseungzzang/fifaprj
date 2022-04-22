package com.fifa.web.bean;

public class PageMakerBean {
	// 현재페이지, 시작페이지, 끝페이지, 게시글 총 갯수, 페이지당 글 갯수, 마지막페이지, SQL쿼리에 쓸 start, end
		private int nowPage, startPage, endPage, total, cntPerPage, lastPage, start, end;
		private int cntPage = 5;
		
		private String vsWriter;
		private String vsOpponent;
		private String searchTypeKeyword;
		public PageMakerBean() {
		}
		public PageMakerBean(int total, int nowPage, int cntPerPage, String vsWriter, String vsOpponent) {
			setNowPage(nowPage); //1
			setCntPerPage(cntPerPage);//10
			setTotal(total);//20
			calcLastPage(getTotal(), getCntPerPage());
			calcStartEndPage(getNowPage(), cntPage);
			calcStartEnd(getNowPage(), getCntPerPage());
			setVsWriter(vsWriter);
			setVsOpponent(vsOpponent);
			setSearchTypeKeyword(vsWriter,vsOpponent);
		}
		// 제일 마지막 페이지 계산
		public void calcLastPage(int total, int cntPerPage) {
			setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
		}
		// 시작, 끝 페이지 계산
		public void calcStartEndPage(int nowPage, int cntPage) {
			setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
			if (getLastPage() < getEndPage()) {
				setEndPage(getLastPage());
			}
			setStartPage(getEndPage() - cntPage + 1);
			if (getStartPage() < 1) {
				setStartPage(1);
			}
		}
		// DB 쿼리에서 사용할 start, end값 계산
		public void calcStartEnd(int nowPage, int cntPerPage) {
			setEnd(cntPerPage);//10 20 
			setStart((nowPage-1)*cntPerPage);//1 - 10, 1 20  1 10 1=0,10 2 10,10 3 20,10
		
		}
		
		public String getSearchTypeKeyword() {
			return searchTypeKeyword;
		}
		public void setSearchTypeKeyword(String vsWriter,String vsOpponent) {
			if(vsWriter != null && vsOpponent ==null) {
				searchTypeKeyword = "&mySearch="+vsWriter;
				System.out.println(searchTypeKeyword);
			}
			else if(vsWriter != null && vsOpponent !=null) {
				searchTypeKeyword = "&userName1="+vsWriter+"&userName2="+vsOpponent;
				System.out.println(searchTypeKeyword);
			}
			
		}
		public int getNowPage() {
			return nowPage;
		}
		public void setNowPage(int nowPage) {
			this.nowPage = nowPage;
		}
		public int getStartPage() {
			return startPage;
		}
		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}
		public int getEndPage() {
			return endPage;
		}
		public void setEndPage(int endPage) {
			this.endPage = endPage;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public int getCntPerPage() {
			return cntPerPage;
		}
		public void setCntPerPage(int cntPerPage) {
			this.cntPerPage = cntPerPage;
		}
		public int getLastPage() {
			return lastPage;
		}
		public void setLastPage(int lastPage) {
			this.lastPage = lastPage;
		}
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}	
		public int setCntPage() {
			return cntPage;
		}
		public void getCntPage(int cntPage) {
			this.cntPage = cntPage;
		}
		
		public void setVsWriter(String vsWriter) {
			this.vsWriter = vsWriter;
		}
		public void setVsOpponent(String vsOpponent) {
			this.vsOpponent = vsOpponent;
		}
		
		@Override
		public String toString() {
			return "PagingVO [nowPage=" + nowPage + ", startPage=" + startPage + ", endPage=" + endPage + ", total=" + total
					+ ", cntPerPage=" + cntPerPage + ", lastPage=" + lastPage + ", start=" + start + ", end=" + end
					+ ", cntPage=" + cntPage + "]";
		}
	}

