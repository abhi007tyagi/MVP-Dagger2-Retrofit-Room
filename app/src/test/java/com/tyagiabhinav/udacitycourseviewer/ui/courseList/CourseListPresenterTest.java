package com.tyagiabhinav.udacitycourseviewer.ui.courseList;

import com.tyagiabhinav.udacitycourseviewer.TestUtils;
import com.tyagiabhinav.udacitycourseviewer.model.CourseRepository;
import com.tyagiabhinav.udacitycourseviewer.model.DataSource;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.ApiResponse;
import com.tyagiabhinav.udacitycourseviewer.model.pojo.Courses;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CourseListPresenterTest {

    private static List<Courses> COURSES;

    @Mock
    private CourseRepository mCourseRepository;

    @Mock
    private CourseListContract.View mView;

    /**
     * {@link ArgumentCaptor} capture argument values and use them to perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<DataSource.GetCourseList> mGetCourseListCaptor;

    private CourseListPresenter mCourseListPresenter;


    @Before
    public void setUp() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mCourseListPresenter = new CourseListPresenter(mCourseRepository);
        mCourseListPresenter.takeView(mView);

        // The presenter won't update the view unless it's active.
        when(mView.isActive()).thenReturn(true);

        COURSES = ((ApiResponse)TestUtils.loadJson("mock/udacity_course_repos.json", ApiResponse.class)).getCourses();
    }

    @Test
    public void loadCourses() {

        mCourseListPresenter.loadCourses(Matchers.eq(false));

        verify(mCourseRepository, times(1)).getCourses(Matchers.eq(false), mGetCourseListCaptor.capture());
        mGetCourseListCaptor.getValue().onCoursesFetched(COURSES);

        // Then progress indicator is shown
        verify(mView,times(1)).setLoadingIndicator(true);
        // Then progress indicator is hidden and all tasks are shown in UI
        verify(mView,times(1)).setLoadingIndicator(false);
        ArgumentCaptor<List> showCoursesArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mView).showCourses(showCoursesArgumentCaptor.capture());
        // there are 209 courses saved in mock json
        assertTrue(showCoursesArgumentCaptor.getValue().size() == 209);
    }

}