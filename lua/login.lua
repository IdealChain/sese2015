#!/usr/bin/env lua

http = require "socket.http"
ltn12 = require("ltn12")

local username = 'tester@goldenlion.tk'
local password = 'test'
local loginUrl = 'http://localhost:8080/api/login'

if #arg < 2 then
	print("Using default credentials '" .. username .. "': '" .. password .. "'");
else 
	username = arg[0]
	password = arg[1]
end

print("Login...")
local responsebody = {}
local requestbody = '{ "username": "'..username..'", "password": "'..password..'" }'
local response, code = http.request{
	method = "POST";
	url = loginUrl;
	source = ltn12.source.string(requestbody);
	headers = {
		['Content-Type'] = 'application/json';
		['Content-Length'] = #requestbody;
	};
	sink = ltn12.sink.table(responsebody);
}

if (code ~= 200) then
	print("Login failed. Code: "..code)
	os.exit(1)
elseif (type(responsebody) ~= "table") then 
	print("Login failed. Unexpected response type"..type(responsebody))
end

local authtokenJSON = table.concat(responsebody)
local token;
for match in string.gmatch(authtokenJSON, '"([^"]*)"') do
	token = match;			-- workaround: last match ==> token
end
print("Received Token: " .. token)
print("Save to file './authtoken")
local fhandle = io.open("authtoken", "w+")
io.output(fhandle)
io.write(token)
fhandle.close()