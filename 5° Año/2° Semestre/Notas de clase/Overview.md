# Horarios
~~~dataview
table without id file.link as Materia, schedule.days as Dia, schedule.hours as Hora
from #subject
where schedule.days
~~~

# Para hacer (Excepto estudiar)
~~~dataviewjs
dv.taskList(dv.pages("#subject").where(page => page.subject).file.tasks.where(task => !task.completed))
~~~

# Régimen de rendida
~~~dataviewjs
dv.table(["Materia", "Parciales", "Proyecto", "Promocional"], dv.pages("#subject").where(page => page.subject).map(page => [page.file.link, (page.midterms) ? "✅" : "❌", (page.project) ? "✅" : "❌", (page.promotion) ? "✅" : "❌"]))
~~~
