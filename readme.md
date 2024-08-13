# GitHub 및 Commit Convention

## 목차
1. [Git Branch Naming Convention](#git-branch-naming-convention)
2. [Git Commit Message Convention](#git-commit-message-convention)
3. [Pull Request(Pull Request) Convention](#pull-requestpull-request-convention)
4. [Code Review Guidelines](#code-review-guidelines)
5. [Issue Naming Convention](#issue-naming-convention)

## Git Branch Naming Convention
브랜치 명명 규칙은 아래와 같이 설정합니다:

- **feature/설명**: 새로운 기능 개발 시
  - 예시: `feature/login-page`
- **bugfix/설명**: 버그 수정 시
  - 예시: `bugfix/fix-login-error`
- **test/설명**: 테스트 관련 작업 시
  - 예시: `test/add-unit-tests`

## Git Commit Me*ssage Convention
커밋 메시지는 아래와 같은 형식을 따릅니다:

### 타입(Type)*
🐛 bug: 버그 수정<br>
✨ sparkles: 새로운 기능 도입<br>
🔥 fire: 코드나 파일 삭제<br>
📝 memo: 문서 추가 또는 업데이트<br>
🎨 art: 코드의 구조/형식 개선<br>
🚧 construction: 작업 진행 중<br>
🎉 tada: 프로젝트 시작<br>
✅ white_check_mark: 테스트 추가, 업데이트 또는 통과<br>
🔧 wrench: 설정 파일 추가 또는 업데이트<br>
📦️ package: 컴파일된 파일이나 패키지 추가 또는 업데이트<br>
♻️ recycle: 코드 리팩토링

### 예시

feat: 로그인 페이지 추가

로그인 페이지에 대한 UI 및 기본 기능을 구현했습니다.

## Pull Request(Pull Request) Convention
- **Title**: `[타입] 간략한 설명`
    - 예시: `[feat] 로그인 페이지 구현`
- **Description**:
    1. 작업 내용 요약
    2. 관련된 이슈 링크
    3. 테스트 방법 및 스크린샷(가능한 경우)

## Code Review Guidelines
- 코드 리뷰는 모든 Pull Request에 대해 최소 1명이 수행해야 합니다.
- 모든 논의는 GitHub의 Pull Request 코멘트를 통해 이루어져야 합니다.
- 리뷰어는 코드의 가독성, 성능, 보안 문제 등을 검토합니다.
- main 브랜치에 merge는 팀장이 진행하거나 팀장의 동의 하에 팀원이 진행합니다.

## Issue Naming Convention
- **Title**: `이슈 설명`
    - 예시: `로그인 버튼이 작동하지 않음`
- **Description**:
    1. 문제 설명 및 재현 방법
    2. 기대 결과
    3. 참고할 자료 (스크린샷, 로그 등)

## 참고 사항
- 모든 작업은 사전에 정의된 컨벤션에 따라 진행합니다.
- 예외 사항이 발생할 경우, 팀 내부에서 논의 후 결정합니다.
