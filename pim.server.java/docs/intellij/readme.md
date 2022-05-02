IntelliJ has built into it a facility to edit and run ".http" files which sounded promising.
It is something between Postman and curl, but the rough edges and proprietary-ness makes it a pass.
But it's something worth keeping track of especially if:

 1. Get rid of the awkward scripting syntax. Why force >{%%} around all your scripts?
 2. Why only ES5 support? (I suspect because it's using an embedded javascript engine) I really miss template strings and arrow functions in this kind of code.
 3. How do I add a 3rd party assertion library?

I'm better off writing ad hoc node or java scripts (or using postman) to get this functionality.
