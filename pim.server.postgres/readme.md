# PIM Server: postgresql

This repo tracks the installation and configuration decisions around the postgres installation.
The assumptions are that the platform doesn't really matter, and that we won't lean heavily into pgsql
features to make porting to other rdbms no harder than it has to be.

By making this a module of it's own, it also gives us breathing room to explore other aspects around this software
like running it under a process watcher, or connecting this process to others, perhaps for replication.

This is also the place that makes sense to record all the ddl that is executed on the server, very much 
in liquibase tradition, with numbered sql files with meaningful names. Note that
we can produce a file in target that represents connection details, and then share it with a 
co-located process through normal unix-y methods. We want each process to have a user associated,
but a group, too, and files like this give group read access to this file.

# Logging changes

Whenever we connect manually to postgres we should keep a record of what changed in this repository.
The database structure should be periodically checked for deviations from this record. Note that 
developer requested changes to the database should be.

# Connectivity

A database is a traditional native program that must bind to a port, and listen for connection
requests over a tcp socket. Databases usually expect to service bundles of long-lived connections 
tied to an app server process. The connections are known to be shared across all request handlers
running on the app server, and pooled under the name "service account".

Alternatively, one could use postgres' own user management system such that a connected user
was actually connected to a database under that users' own identity. What kind of real-world
limit is there to pgsql's concurrency? Which resources start to run out on a $5 VPS?

BTW what would it take for people to trust me to run their business? Or any part of it?

Exposing a database as an API is probably what most devs would be comfortable with now. Of interest is that
by default most of the real data-design is done in SQL. Rich types are only useful in simulations because they help 
programmers deal with their own complexity. (UIs are simulations, by the way. Of paperwork). But that said, I don't think the 
relational db is enough, in particular the datatypes aren't quite rich enough, and there aren't any rules 
about measuring and error bars and units and provenance. The notion of combining values to form a cloud that represents
your synthesized knowledge, is not something that comes built-in to a relational database. In that sense it misleads 
people into believing that the relational model is enough. That it's okay to associate a precise value without
provenance, with a measurement in or around another human being, and we're going to take that seriously and 
even do violence based on that value. That said, maybe once you get a panopticon on someone, your perspective
changes and it becomes clear who that person is, what their struggles are, maybe even *what their problem is*, at a deep 
level. I suppose I would value more knowing the objective truth than I would be protecting myself, if we lived in a perfect world.
But we cannot reveal ourselves fully because it makes us vulnerable, it makes others uncomfortable, because it is embarrasing
and shameful, because everyone knows suffering is universal, the only differnce is you think yours is special enough to complain about.

And yet we still react to egregious injustice. It's a good thing, too! I think this makes us human. And its unfortunate that
this positive, energetic form of fixing things, righting the wrongs, can be pointed in the wrong direction with lies
but its better to suffer through this extraordinarily long-lived mistake of my brothers, than to crush the spirit 
that would do anything, including violence, to avoid a catastrophic injustice from which there is no recovery.

This is a very strong commitment to something, to a fair and right outcome. But please, before you throw your 
life away in a brave and patriotic manner (a heroic act and I am being totally sincere), would it not make sense
to spend extra time and effort to verify that your beliefs are true? In particular, what is the evidence that the election
was stolen? Are you comfortable taking the other side of this debate? Do you know what the other side is actually saying?

To the left I would say: please, before you justify a race war with your reverse racism, and reverse sexism, 
and please stop denying that's what it is. I get it you think white men have it easy, everything handed to them
on a plate. I'd have thought a century of cinema exploring white lives would have served at least some purpose, 
in this case convincing you that whites are not alike, men are not alike, and sometimes circumstances can be difficult
because of the presence of mental illness that does not interfere with working or reproducing. But us white men
especially those of us who are called intelligent, and consider ourselves skeptical or iconoclastic, who are
egalitarian but also somewhat ruthlessly meritocratic. We are the ones who want to see diversity on the podium.
We treat everyone on their merits, and very glad to meet good people of any kind, and particularly those Others
that prove an exception to some racist stereotype.

I am familiar with these, being Jewish. But it seems, sadly, that rather than that going away completely,
it's instead being emulated: greed and self-interest have become beacons of faith, and the prosperity gospel
has actually given Jews a great deal of moral high ground. Judaism is a strange religion based on a sense
of innate superiority based on their special bond with the One Deity. "Follow these rules, and you shall dominate your foes"
so says the bible. And yet, historically the jews have had their ass kicked, until Jesus came along and added
A Lesson. It was a lesson about Humility and the Evil within us all. That it can rise up and kill even the best
among us, those with the least urge to harm, the most urge to help, with the most forgiving heart. He was a 
threat and they killed him, and his followers, in turn, martyred him and lit fire to the tinders of judaism, 
raging into a fire of Christendom. And what a strange fire that was, so violent and passionate for hundreds of years.
The Reformation and religious tolerance came during the industrial revolution, when capable people were in short supply.
And a lifetime of intellectual study and argument made Jews particularly useful in the more intellectually
demanding aspects of economic life. Endured for their usefulness, and handed "dirty" jobs like money-lending,
or "silly" jobs like recording actors on film, Jews are over-represented in several industries. (Not only were Jews smart
to begin with, but the Holocaust killed many of us, arguably those who were, on average, less prescient to the existential
threat of staying in Europe. So American Jewry were smarter, and less sentimental, and got out and could 
start over here. And they tended to stick together and celebrate and pray together, even here. But they
also assimilated. It turns out if you put a Jewish and a Christian kid through an American upbringing, they're
going to end up in roughly the same place, on average. The biggest difference may be ambition, as Jewish parents are 
often pushing their kids to become a professional of some kind. Heaven help them if their kid wants to be an artist.)

But the most important things, like expectations around work and power, probably aren't predicted. I would expect
the Jew to be better read and informed; probably more fun to talk to. I would expect the women to be more careful,
to be less care-free and less risk-taking, and more calculating (yes, in terms of income). Christian women seem 
to be more carefree and open to all kinds of lifestyles and beliefs and personalities. They are more open to 
a kind of bohemian creativity that may threaten a more conservative jewish girl. But yes, I would love to 
know a manic pixie dreamgirl of any variety. It would even be cool if there was some path where we could
really come to know and cherish and forgive each other forever and ever. But that, it seems, requires two
people with compatible mental health issues! Some issues don't mix at all, like anxiety in her and anger in him.
Or sullen depressed silence in both. But, problems she wants to fix about herself, and his desire to control her, 
could work (that is basically the creative plot resolution of "Secretary").

(It would probably be enough to just produce lots of cool stuff and get known as a (minor) creative genius
to attract those discerning beautiful women. I'm thinking about getting matched up like the SNL dudes.
In particular, Andy Samberg marrying pixie dream girl Joanna Newsom, and apparently having a great time.)

It can be hard to grow a vision in all the noise. It's especially hard to do this when the vision is necessarily
shaped by the noise (which it must be in the field of software engineering).

How do you want to run your stuff? As a software developer making a java dropwizard + postgresql program 
targeted at a single $5/mo Ubuntu VPS?

```
On Linux
  $ apt-get install git java17 postgresql
On Windows
  PS>choco install git java17 postgresql
  PS>scoop install git java17 postgresql
On macOS
  $ brew install git java17 postgresql

[get the code]
git clone github.com/javajosh/javajosh-pim
cd pim-server-postgres
[bring postgres up]

[connect to postgres]
psql -connect localhost:4949 -user default -pass default -exec ./setup/*.sql
C:\java\pgsql\14\data\pghba.conf
```

One interesting alternative to the binary java installation is to compile it yourself from source.
This is easier than it sounds, and really gives a large degree of control over your runtime.
You can, for example, use powerful native debugging tools like dtrace, on the jvm and your code,
allowing you to look deeper for troubleshooting or improving single-process performance.

It would be interesting to see if there are any practical, small source-based distributions.
Something like Alpine plus the source and toolchain to build Alpine. Should be able to make 
small modifications to any package, by simply invalidating built artifacts back to the change.

Another alternative
