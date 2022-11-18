@startuml
autonumber
client -> server : register
client <- server : broadcast userList
client -> server : send message
client <- server : broadcast

@enduml

@startuml

package com.controller{
	class TcpController
	enum MsgEnum 
}

MsgEnum --* TcpController : use

package com.module{
	class ClientDao
	class ServerProperties
}

ServerProperties -* ClientDao : use
package com.viewer{
	class Viewer
}

Viewer ..> TcpController
ClientDao ..> TcpController
@enduml
