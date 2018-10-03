package co.za.school.dashboard.web.rest;

import co.za.school.dashboard.SchooldashboardApp;

import co.za.school.dashboard.domain.Coarse;
import co.za.school.dashboard.repository.CoarseRepository;
import co.za.school.dashboard.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static co.za.school.dashboard.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.za.school.dashboard.domain.enumeration.YearOfStudy;
/**
 * Test class for the CoarseResource REST controller.
 *
 * @see CoarseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchooldashboardApp.class)
public class CoarseResourceIntTest {

    private static final Integer DEFAULT_COURSE_ID = 1;
    private static final Integer UPDATED_COURSE_ID = 2;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DURATION = 1;
    private static final Integer UPDATED_DURATION = 2;

    private static final Integer DEFAULT_COST = 1;
    private static final Integer UPDATED_COST = 2;

    private static final YearOfStudy DEFAULT_LEVEL = YearOfStudy.ONE;
    private static final YearOfStudy UPDATED_LEVEL = YearOfStudy.TWO;

    @Autowired
    private CoarseRepository coarseRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCoarseMockMvc;

    private Coarse coarse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CoarseResource coarseResource = new CoarseResource(coarseRepository);
        this.restCoarseMockMvc = MockMvcBuilders.standaloneSetup(coarseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Coarse createEntity(EntityManager em) {
        Coarse coarse = new Coarse()
            .courseId(DEFAULT_COURSE_ID)
            .name(DEFAULT_NAME)
            .duration(DEFAULT_DURATION)
            .cost(DEFAULT_COST)
            .level(DEFAULT_LEVEL);
        return coarse;
    }

    @Before
    public void initTest() {
        coarse = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoarse() throws Exception {
        int databaseSizeBeforeCreate = coarseRepository.findAll().size();

        // Create the Coarse
        restCoarseMockMvc.perform(post("/api/coarses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coarse)))
            .andExpect(status().isCreated());

        // Validate the Coarse in the database
        List<Coarse> coarseList = coarseRepository.findAll();
        assertThat(coarseList).hasSize(databaseSizeBeforeCreate + 1);
        Coarse testCoarse = coarseList.get(coarseList.size() - 1);
        assertThat(testCoarse.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testCoarse.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCoarse.getDuration()).isEqualTo(DEFAULT_DURATION);
        assertThat(testCoarse.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testCoarse.getLevel()).isEqualTo(DEFAULT_LEVEL);
    }

    @Test
    @Transactional
    public void createCoarseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coarseRepository.findAll().size();

        // Create the Coarse with an existing ID
        coarse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoarseMockMvc.perform(post("/api/coarses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coarse)))
            .andExpect(status().isBadRequest());

        // Validate the Coarse in the database
        List<Coarse> coarseList = coarseRepository.findAll();
        assertThat(coarseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCourseIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = coarseRepository.findAll().size();
        // set the field null
        coarse.setCourseId(null);

        // Create the Coarse, which fails.

        restCoarseMockMvc.perform(post("/api/coarses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coarse)))
            .andExpect(status().isBadRequest());

        List<Coarse> coarseList = coarseRepository.findAll();
        assertThat(coarseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = coarseRepository.findAll().size();
        // set the field null
        coarse.setName(null);

        // Create the Coarse, which fails.

        restCoarseMockMvc.perform(post("/api/coarses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coarse)))
            .andExpect(status().isBadRequest());

        List<Coarse> coarseList = coarseRepository.findAll();
        assertThat(coarseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDurationIsRequired() throws Exception {
        int databaseSizeBeforeTest = coarseRepository.findAll().size();
        // set the field null
        coarse.setDuration(null);

        // Create the Coarse, which fails.

        restCoarseMockMvc.perform(post("/api/coarses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coarse)))
            .andExpect(status().isBadRequest());

        List<Coarse> coarseList = coarseRepository.findAll();
        assertThat(coarseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCoarses() throws Exception {
        // Initialize the database
        coarseRepository.saveAndFlush(coarse);

        // Get all the coarseList
        restCoarseMockMvc.perform(get("/api/coarses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coarse.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].duration").value(hasItem(DEFAULT_DURATION)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL.toString())));
    }
    
    @Test
    @Transactional
    public void getCoarse() throws Exception {
        // Initialize the database
        coarseRepository.saveAndFlush(coarse);

        // Get the coarse
        restCoarseMockMvc.perform(get("/api/coarses/{id}", coarse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coarse.getId().intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCoarse() throws Exception {
        // Get the coarse
        restCoarseMockMvc.perform(get("/api/coarses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoarse() throws Exception {
        // Initialize the database
        coarseRepository.saveAndFlush(coarse);

        int databaseSizeBeforeUpdate = coarseRepository.findAll().size();

        // Update the coarse
        Coarse updatedCoarse = coarseRepository.findById(coarse.getId()).get();
        // Disconnect from session so that the updates on updatedCoarse are not directly saved in db
        em.detach(updatedCoarse);
        updatedCoarse
            .courseId(UPDATED_COURSE_ID)
            .name(UPDATED_NAME)
            .duration(UPDATED_DURATION)
            .cost(UPDATED_COST)
            .level(UPDATED_LEVEL);

        restCoarseMockMvc.perform(put("/api/coarses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCoarse)))
            .andExpect(status().isOk());

        // Validate the Coarse in the database
        List<Coarse> coarseList = coarseRepository.findAll();
        assertThat(coarseList).hasSize(databaseSizeBeforeUpdate);
        Coarse testCoarse = coarseList.get(coarseList.size() - 1);
        assertThat(testCoarse.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testCoarse.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCoarse.getDuration()).isEqualTo(UPDATED_DURATION);
        assertThat(testCoarse.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testCoarse.getLevel()).isEqualTo(UPDATED_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingCoarse() throws Exception {
        int databaseSizeBeforeUpdate = coarseRepository.findAll().size();

        // Create the Coarse

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoarseMockMvc.perform(put("/api/coarses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coarse)))
            .andExpect(status().isBadRequest());

        // Validate the Coarse in the database
        List<Coarse> coarseList = coarseRepository.findAll();
        assertThat(coarseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoarse() throws Exception {
        // Initialize the database
        coarseRepository.saveAndFlush(coarse);

        int databaseSizeBeforeDelete = coarseRepository.findAll().size();

        // Get the coarse
        restCoarseMockMvc.perform(delete("/api/coarses/{id}", coarse.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Coarse> coarseList = coarseRepository.findAll();
        assertThat(coarseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Coarse.class);
        Coarse coarse1 = new Coarse();
        coarse1.setId(1L);
        Coarse coarse2 = new Coarse();
        coarse2.setId(coarse1.getId());
        assertThat(coarse1).isEqualTo(coarse2);
        coarse2.setId(2L);
        assertThat(coarse1).isNotEqualTo(coarse2);
        coarse1.setId(null);
        assertThat(coarse1).isNotEqualTo(coarse2);
    }
}
