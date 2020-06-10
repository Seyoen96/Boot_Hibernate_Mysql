package com.sy.s1.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// annotation 쓰지 않아도 repository로 사용 가능
// <테이블의 정보 가지고 있는 타입, primary key의 데이터타입>
public interface MemberRepository extends JpaRepository<MemberVO, String> {
	
	// 내장 메서드
	// save() : insert
	// 쿼리문 대신 메서드가 제공
	// 메서드를 통해서 쿼리문이 자동으로 생성
	
	// 기본 제공 메서드
	// S save(T)					: insert, update
	// <S extends T> S save(Interable<? extends T>)	 : 다중 insert, update
	// void deleteById(ID)			: PK를 통한 삭제
	// void delete(T)				: 주어진 Entity delete
	// void deleteAll(Interable<? extends T>) : 주어진 모든 Entity 삭제
	// void deletAll()				: 모든 Entity 삭제
	// List<T> findAll() 			: 모든 Entity select
	// Optional<T> findById(ID)		: PK로 단일 Entity select
	// long count()					: Entity의 모든 갯수
	// boolean existsById(ID)		: PK로 Entity 존재 여부  
	
	// 사용자가 생성하는 메서드, 쿼리 메서드
	// 메서드 자체가 쿼리문이 되는 것
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence
	
	// public abstract ...
	// select * from member where id=? and pw=?
	// 매개변수에는 MemberVO을 넣어도 자동 매핑 되지 X
	// 매개변수명과 상관없이 메서드 명에 명시된 순서대로 찾아감
	public MemberVO findByIdAndPw(String id, String pw);

	
}
