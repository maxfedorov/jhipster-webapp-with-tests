describe('/todo', () => {
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';

  beforeEach(() => {
    cy.login(username, password);
    cy.visit('/todo');
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/todo').as('todoList');
    cy.intercept('POST', '/api/todo').as('todoSave');
  });

  it('should be accessible through menu', () => {
    cy.visit('');
    cy.get('[data-cy="todo"]').click({force: true});
    cy.url().should('match', /\/todo$/);
  });

  it("should be able to change 'user' firstname settings", () => {
    cy.get('[data-cy="todoTitleInput"]').clear().type('test');
    cy.get('[data-cy="todoCreate"]').click({force: true});
    cy.wait('@todoSave').then(({response}) => expect(response.statusCode).to.equal(200));
  });
});
