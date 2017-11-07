package com.revature.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("aspects")
public class ScrumAspects {

	private static final Logger log = LogManager.getRootLogger();

	@Before("execution(* createNewScrumBoard*(..))")
	public void beforeCreatingNSB(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(* createNewScrumBoard*(..))")
	public void afterCreatingNSB(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* createNewScrumBoard*(..))")
	public void afterCreatingNSBFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(* createNewScrumBoardTask*(..))")
	public void beforeCreatingNSBT(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(* createNewScrumBoardTask*(..))")
	public void afterCreatingNSBT(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* createNewScrumBoardTask*(..))")
	public void afterCreatingNSBTFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(* getAllUsers*(..))")
	public void beforeGettingAllUsers(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(* getAllUsers*(..))")
	public void afterGettingAllUsers(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* getAllUsers*(..))")
	public void afterGettingAllUsersFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}
	// --------------------------------------------------------------------------

	@Before("execution(* getScrumUserById*(..))")
	public void beforeGettingUserById(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(* getScrumUserById*(..))")
	public void afterGettingUserByIdSucceeds(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* getScrumUserById*(..))")
	public void afterGettingUserByIdFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}
	// --------------------------------------------------------------------------

	@Before("execution(* getScrumUserByUsername*(..))")
	public void beforeGettingSU(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(* getScrumUserByUsername*(..))")
	public void afterGetSUSucceeds(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* getScrumUserByUsername*(..))")
	public void afterGetSUFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(* getScrumBoardLanes*(..))")
	public void beforeGettingSBL(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(* getScrumBoardLanes*(..))")
	public void afterGettingSBL(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* getScrumBoardLanes*(..))")
	public void afterGettingSBLFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  getScrumBoardStoryById*(..))")
	public void beforeGettingSBS(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  getScrumBoardStoryById*(..))")
	public void afterGettingSBS(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(*  getScrumBoardStoryById*(..))")
	public void afterGettingSBSFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  getScrumBoardTaskById*(..))")
	public void beforeGettingSBT(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  getScrumBoardTaskById*(..))")
	public void afterGettingSBT(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(*  getScrumBoardTaskById*(..))")
	public void afterGettingSBTFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  getScrumBoardById*(..))")
	public void beforeGettingSBById(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  getScrumBoardById*(..))")
	public void afterGettingSBById(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(*  getScrumBoardById*(..))")
	public void afterGettingSBByIdFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  updateScrumUser*(..))")
	public void beforeUpdateScrumUser(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  updateScrumUser*(..))")
	public void afterUpdateScrumUser(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(*  updateScrumUser*(..))")
	public void afterUpdateScrumUserFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  updateScrumBoard*(..))")
	public void beforeUpdateScrumBoard(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  updateScrumBoard*(..))")
	public void afterUpdateScrumBoard(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(*  updateScrumBoard*(..))")
	public void afterUpdateScrumBoardFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  updateScrumBoardStory*(..))")
	public void beforeUpdateScrumBoardStory(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  updateScrumBoardStory*(..))")
	public void afterUpdateScrumBoardStory(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(*  updateScrumBoardStory*(..))")
	public void afterUpdateScrumBoardStoryFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  updateScrumBoardTask*(..))")
	public void beforeUpdateScrumBoardTask(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  updateScrumBoardTask*(..))")
	public void afterUpdateScrumBoardTask(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(*  updateScrumBoardTask*(..))")
	public void afterUpdateScrumBoardStoryTaskFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  editExistingScrumBoard*(..))")
	public void beforeEditExistingScrumBoard(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  editExistingScrumBoard*(..))")
	public void afterEditExistingScrumBoard(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(*  editExistingScrumBoard*(..))")
	public void afterEditExistingScrumBoardFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  addUserToBoard*(..))")
	public void beforeAddUserToBoard(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  addUserToBoard*(..))")
	public void afterAddUserToBoard(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(*  addUserToBoard*(..))")
	public void afterAddUserToBoardFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  changeScrumBoardStoryLane*(..))")
	public void beforeChangeScrumBoardStoryLane(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  changeScrumBoardStoryLane*(..))")
	public void afterChangeScrumBoardStoryLane(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* changeScrumBoardStoryLane*(..))")
	public void afterChangeScrumBoardStoryLaneFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  updateScrumBoardTaskStatus*(..))")
	public void beforeUpdateScrumBoardTaskStatus(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  updateScrumBoardTaskStatus*(..))")
	public void afterUpdateScrumBoardTaskStatus(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* updateScrumBoardTaskStatus*(..))")
	public void afterUpdateScrumBoardTaskStatusFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  mainRequest*(..))")
	public void beforeMainRequest(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  mainRequest*(..))")
	public void afterMainRequest(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* mainRequest*(..))")
	public void afterMainRequestFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

	// --------------------------------------------------------------------------
	@Before("execution(*  authenticateLogin*(..))")
	public void beforeAuthenticateLogin(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  authenticateLogin*(..))")
	public void afterAuthenticateLogin(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* authenticateLogin*(..))")
	public void afterAuthenticateLoginFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}
	// --------------------------------------------------------------------------
	@Before("execution(*  logout*(..))")
	public void beforeLogout(JoinPoint jp) {
		log.warn("BEFORE: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterReturning("execution(*  logout*(..))")
	public void afterLogout(JoinPoint jp) {
		log.warn("SUCCESS: " + jp.getKind() + " - " + jp.getSignature());
	}

	@AfterThrowing("execution(* logout*(..))")
	public void afterLogoutFails(JoinPoint jp) {
		log.error("FAILURE: " + jp.getKind() + " - " + jp.getSignature());
	}

}
