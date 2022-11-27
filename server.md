@startuml
autonumber
client -> server : register
client <- server : broadcast userList
client -> server : send message
client <- server : broadcast

@enduml

@startuml
package serverDao{
	enum MsgEnum
	class ServerThread
	interface TcpBroadcast
	class TcpServerDao
	class User
}
MsgEnum -down-* ServerThread
User -down-* ServerThread
TcpBroadcast-|> ServerThread
ServerThread -o TcpServerDao

package controler{
	interface TcpBroadcast
	class TcpController
}

TcpServerDao -o TcpController 

@enduml
