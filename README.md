# <나만의 일정 관리 앱 서버 만들기>

## 1. PRD 요구사항 정의서/기능정의서
| 번호 	| 요구사항명     	| 요구사항 상세                                                                                            	| 중요도 	| 비고                                                                                                                        	|
|------	|----------------	|----------------------------------------------------------------------------------------------------------	|--------	|-----------------------------------------------------------------------------------------------------------------------------	|
| 1    	| 일정 등록      	| • 할일 제목, 할일 내용, 담당자(작성자), 비밀번호, 작성일을 일정으로 등록                                 	| 상     	| • 작성일은 작성한 날짜=오늘                                                                                                 	|
| 2    	| 일정 목록 조회 	| • 모든 일정 목록 불러와서 조회                                                                           	| 상     	| • 작성일기준 내림차순으로 정렬                                                                                              	|
| 3    	| 일정 조회      	| • 일정을 선택, 할일 제목, 할일 내용 조회                                                                 	| 상     	| • 일정이 없을 때, 에러 메시지 발생                                                                                          	|
| 4    	| 일정 수정      	| • 일정을 선택, 할일 제목, 할일 내용, 담당자   수정가능<br>• 수정된 일정의 정보를 반환 받아 화면에서 확인 	| 상     	| • 비밀번호 함께 전달해 일치할 경우에만 가능<br>• 비밀번호 불일치 시, 에러 메시지 발생                                       	|
| 5    	| 일정 삭제      	| • 일정을 선택, 삭제                                                                                      	| 상     	| • 비밀번호 함께 전달해 일치할 경우에만 가능<br>• 비밀번호 불일치 시, 에러 메시지 발생<br>• 일정이 없을 때, 에러 메시지 발생 	|
## 2. Use Case Diagram

![Use_Case_Diagram](https://github.com/andrew75313/Sparta-Week5-Assignment/assets/161192870/b0afc5af-50be-448a-a76b-d7dfbbf7bab2)
<br>( http://localhost:8080 진입 시 정적화면 = index.html로 구현이 되었을 때를 가정 )

## 3. ERD

![ERD](https://github.com/andrew75313/Sparta-Week5-Assignment/assets/161192870/fca19e6d-79df-41eb-b228-787e08a77666)

## 4. API 명세서
| 기능           	| Method 	| URL                 	| Return                    	| response                                       	|
|----------------	|--------	|---------------------	|---------------------------	|------------------------------------------------	|
| 일정 등록      	| POST   	| /api/schedules      	| ScheduleResponseDto       	| 등록된 책 정보 or 일정 등록에 대한 성공 여부   	|
| 일정 목록 조회 	| GET    	| /api/schedules      	| List<ScheduleResponseDto> 	| 등록된 일정들의 정보                           	|
| 일정 조회      	| GET    	| /api/schedules/{id} 	| ScheduleResponseDto        	| 선택된 일정의 정보                             	|
| 일정 수정      	| PUT    	| /api/schedules/{id} 	| Long                      	| 수정된 일정 정보 or 일정 등록에 대한 성공 여부 	|
| 일정 삭제      	| DELETE 	| /api/schedules/{id} 	| Long                      	| 일정삭제에 대한 성공 여부                      	|