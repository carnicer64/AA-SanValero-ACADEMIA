<ol class="list-group list-group-numbered">
          <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
              <a href="/academia-servlet/studentList.jsp" class="fw-bold">Lista de alumnos</a>
            </div>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
              <a href="/academia-servlet/searchStudent.jsp" class="fw-bold">Busqueda de alumnos</div>
            </div>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
              <a href="/academia-servlet/subjectList.jsp" class="fw-bold">Lista de cursos</a>
            </div>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
              <a href="/academia-servlet/searchSubjects.jsp" class="fw-bold">Busqueda de cursos</div>
            </div>
          </li>
          <%
            if ((currentUser != null) && (currentUser.getRole().equals("Admin"))) {
          %>
          <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
              <a href="/academia-servlet/addModifyStudent.jsp" class="fw-bold">Registro de nuevo alumno</a>
            </div>
          </li>
          <li class="list-group-item d-flex justify-content-between align-items-start">
            <div class="ms-2 me-auto">
              <a href="/academia-servlet/addModifySubject.jsp" class="fw-bold">Registro de nuevo curso</a>
            </div>
          </li>
          <%
            }
          %>
        </ol>