<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en-US">
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=2.0; user-scalable=yes;">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="Rosetta Stone">
<meta charset="UTF-8">
<!--<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,FF=3,chrome=1" />
<title>Mock RS API</title>
</head>
<body>
	<button id="org">org</button>
	<button id="user">update user</button>
	
	<script src="assets/jquery/jquery-2.1.1.min.js" type='text/javascript'></script>
	<script type="text/javascript">
		var userdata = {
			    "_id": "566206c4-76a4-4a66-80e3-75af49d73212",
			    "_rev": "98-1a1a1a4f8be11115984cee5f197e04ea",
			    "externalIds": { "rsa": "rsaUsername"},
			    "userField4": "1",
			    "notes": "test",
			    "creator": "566206c4-76a4-4a66-80e3-75af49d73212",
			    "lastName": "only",
			    "creationDate": "2014-01-10T14:18:08+0000",
			    "localizationLanguage": "en-US",
			    "wallet": "879d08a3-9b21-4fc7-82b8-c5bc3cc537df",
			    "timezone": "America/Juneau",
			    "preferences": {
			        "voiceType": "male"
			    },
			    "targetLanguage": {
			        "language": "en-US"
			    },
			    "firstName": "SuperOA",
			    "roles": [
			        "role.SuperOrganizationAdmin"
			    ],
			    "organization": "0b0e71af5e82725587827d024cd9f05a",
			    "modificationDate": "2014-11-25T18:41:05+0000",
			    "email": "soa-only@rosettastone.com",
			    "type": "User",
			    "isActive": true,
			    "registrationDate": "2014-01-10T14:18:08+0000",
			    "originLanguage": "el-GR",
			    "client": "TODO",
			    "userField2": "1",
			    "userField1": "test",
			    "userField5": "1",
			    "userField6": "1",
			    "userField3": "1",
			    "description": "description user"
			};
		var orgdata = {
			    "creationDate": "2013-07-10T19:48:17+0000",
			    "endDate": "2023-05-30T18:30:00.000Z",
			    "test": "true",
			    "name": "RSA-TEST2",
			    "rsaServers": [
			        "https://luna.tellmemore.com"
			    ],
			    "seats": 1000000,
			    "namespace": "rsatest2",
			    "startDate": "2013-06-30T18:30:00.000Z",
			    "_rev": "172-f68793df13a05789aa494a2d159d2245",
			    "adminRole": "role.OrganizationAdmin",
			    "modificationDate": "2014-07-29T00:53:48+0000",
			    "disableBeans": false,
			    "type": "Organization",
			    "_id": "0b0e71af5e82725587827d024cd9f05a",
			    "description" : "description org",
			    "admins": [
			        "267aa347-8463-4f06-8c1a-a499adfcff12",
			        "b413aba6-8e52-4c3f-beb4-3b7a861be631",
			        "f898a533-31a9-474d-a29c-3d372f3307db",
			        "2a490a91-2781-4b1a-9187-7dd41132c33c",
			        "e7300bbe-d764-4e8a-b64b-e2475e1c5dd6",
			        "3c3242ae-538c-4080-94f2-a95af5b7b6b6",
			        "04a96db9-8d66-47db-a455-631ffb32299d",
			        "39c01717-4c55-4666-9b65-12ee203f5a36",
			        "752b1988-76b1-48b9-96b3-b6c3b274e5c7",
			        "12e47f18-e9bf-43bd-a064-dc9e71ac78db",
			        "de900218-ff6a-4a50-8325-43419da560e8",
			        "a3f33171-d7fb-4209-92db-55961b5969eb",
			        "6924fa92-9248-4b0c-9241-6a16321a21ae",
			        "47a82ec5-f45a-4bbd-ae90-c6b9019f8887",
			        "9275deed-db7a-40ee-80ff-fadfd06634ee",
			        "67573155-2e4b-41cf-8334-7ce5a92b6346",
			        "f7a5e615-c25c-4e48-a926-4d6f74269304",
			        "0f97428b-c07c-458b-b147-4dd1d63265d9",
			        "0be942e1-20ab-4ed1-ae2d-71ef09cb36d2",
			        "159c38d0-e365-4a94-8152-f708fa14393c",
			        "17e05086-5d88-4cdc-941b-75bdbd3db6f4",
			        "ca2c3d31-cc4d-42d6-b66e-3ac66df1c9b0",
			        "37f262ef-5e7a-49af-9ecd-cf5272f2baf5",
			        "36e31e2a-f45c-44b6-ba8a-ebc199790a71",
			        "2a636e01-b3e4-4b8d-96b3-00983459cfba",
			        "5b48b403-c8fc-4edb-841a-777d3d7c81f8",
			        "a3592ea3-5df0-4cfe-b01f-46d87a960c00",
			        "8ca7f0d9-6f13-4d11-837e-159c9d922fb0",
			        "608ecc33-2de3-4805-b8af-e533e31fd391",
			        "b0050569-43b7-4f20-920a-280dd2d8535d",
			        "9c3f7d16-ff36-45e9-a355-14bd7f15fbbb",
			        "48a2ea27-c5ea-4257-a9cf-a4017ccfa9f5",
			        "f62d7777-6f2e-42c2-ad5f-61a17a2a97a0",
			        "d9f89527-90d4-48aa-a332-6ffcaa104b99",
			        "420af2ff-1054-4811-949c-ae7af4cbb2f7",
			        "56c540b2-51f1-4586-9a45-752d3d9a2290",
			        "a34114c9-71e5-4b18-b256-2f1e7bb3eb53",
			        "4011e3d6-9ac6-4f4d-a9a5-559f3f7b9c2c",
			        "d849ef60-1639-451a-a8b5-c1bbc2979ab7",
			        "f2477edc-400d-4377-89c7-a65e63c880a3",
			        "39dbd162-c334-461f-be4a-8c8d4f8eeb0c",
			        "814bdfee-3767-4f00-b7b2-41c796b84337",
			        "bcc12fcd-9948-438b-b234-28afc4ae3094"
			    ],
			    "configuration": {
			        "allowSearchingByActivitySetIdInEditor": "true",
			        "showAriaInterface": "true"
			    }
			}
		
		var token = "12c44bbb-4d70-42c7-b7c6-750bb41ac698";
		$(document).ready(function(){
			$("#org").click(function(v){
				$.ajax({
		              type: "POST",
		              headers:{"Authorization" : "Bearer " + token},
		              //contentType: "application/json; charset=utf-8",
		              //url: "https://scholar-dev.dev.rosettastone.com/organization",
		              url: "https://localhost/luna/v1/auth/test",
		              data: orgdata,
		              dataType: "json",
// 		              jsonp:"callback",
		              success: function(response){
		                  console.log(response);
		              },
		              error: function(){
		            	  console.log(Arguments)
		              }
		            });  
			})
			
			$("#user").click(function(v){
				$.ajax({
		              type: "POST",
		              headers:{"Authorization" : "Bearer " + token},
		              //contentType: "application/json; charset=utf-8",
		              //url: "https://scholar-dev.dev.rosettastone.com/organization",
		              url: "https://localhost/luna/v1/auth/test",
		              data: userdata,
		              dataType: "json",
// 		              jsonp:"callback",
		              success: function(response){
		                  console.log(response);
		              },
		              error: function(){
		            	  console.log(Arguments)
		              }
		            });  
			})
		})
	</script>
</body>
</html>